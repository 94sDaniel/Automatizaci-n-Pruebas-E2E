package com.orangehrm.automation.tasks;

import com.orangehrm.automation.commons.EmployeeData;
import com.orangehrm.automation.commons.ResourceHelper;
import com.orangehrm.automation.ui.AddEmployeePage;
import com.orangehrm.automation.ui.PimPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Clear;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.Scroll;
import net.serenitybdd.screenplay.actions.Upload;
import net.serenitybdd.screenplay.waits.WaitUntil;

import java.nio.file.Paths;
import java.time.Duration;
import java.util.concurrent.ThreadLocalRandom;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isClickable;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class AddEmployee implements Task {

    private static final Duration EMPLOYEE_SAVE_TIMEOUT = Duration.ofSeconds(30);

    private final EmployeeData employeeData;

    public AddEmployee(EmployeeData employeeData) {

        this.employeeData = employeeData;
    }

    public static AddEmployee withData(EmployeeData employeeData) {
        return instrumented(AddEmployee.class, employeeData);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        String photo = ResourceHelper.imagePathOrPlaceholder(employeeData.getPhotoPath());
        String candidateId = employeeData.getEmployeeId();
        int attempts = 0;
        boolean saved = false;
        actor.attemptsTo(
                WaitUntil.the(PimPage.ADD_BUTTON, isClickable()),
                Click.on(PimPage.ADD_BUTTON),
                WaitUntil.the(AddEmployeePage.FIRST_NAME, isVisible()),
                Enter.theValue(employeeData.getFirstName()).into(AddEmployeePage.FIRST_NAME),
                Enter.theValue(employeeData.getLastName()).into(AddEmployeePage.LAST_NAME),
                Upload.theFile(Paths.get(photo)).to(AddEmployeePage.PHOTO)
        );

        while (!saved && attempts < 5) {
            actor.attemptsTo(
                    Scroll.to(AddEmployeePage.EMPLOYEE_ID),
                    Clear.field(AddEmployeePage.EMPLOYEE_ID),
                    Enter.theValue(candidateId).into(AddEmployeePage.EMPLOYEE_ID),
                    Scroll.to(AddEmployeePage.SAVE_BUTTON),
                    WaitUntil.the(AddEmployeePage.SAVE_BUTTON, isClickable())
                            .forNoMoreThan(EMPLOYEE_SAVE_TIMEOUT.toSeconds()).seconds(),
                    Click.on(AddEmployeePage.SAVE_BUTTON)
            );

            try {
                actor.attemptsTo(
                        WaitUntil.the(AddEmployeePage.SUCCESS_TOAST, isVisible())
                                .forNoMoreThan(EMPLOYEE_SAVE_TIMEOUT.toSeconds()).seconds(),
                        WaitUntil.the(AddEmployeePage.PERSONAL_DETAILS_HEADER, isVisible())
                                .forNoMoreThan(EMPLOYEE_SAVE_TIMEOUT.toSeconds()).seconds()
                );
                saved = true;
            } catch (Exception ignored) {
                attempts++;
                candidateId = generateRandomEmployeeId();
                actor.attemptsTo(
                        WaitUntil.the(AddEmployeePage.EMPLOYEE_ID_ERROR, isVisible())
                                .forNoMoreThan(5).seconds()
                );
            }
        }

        if (!saved) {
            throw new IllegalStateException("Employee could not be saved after multiple ID attempts");
        }
    }

    private String generateRandomEmployeeId() {
        int suffix = ThreadLocalRandom.current().nextInt(10000, 99999);
        return "ID-" + suffix;
    }
}
