package com.orangehrm.automation.tasks;

import com.orangehrm.automation.commons.EmployeeData;
import com.orangehrm.automation.ui.DirectoryPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.waits.WaitUntil;
import org.openqa.selenium.Keys;

import java.time.Duration;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isClickable;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class SearchEmployeeInDirectory implements Task {

    private static final Duration SUGGESTION_WAIT = Duration.ofSeconds(5);
    private final EmployeeData employee;

    public SearchEmployeeInDirectory(EmployeeData employee) {
        this.employee = employee;
    }

    public static SearchEmployeeInDirectory byName(EmployeeData employee) {
        return instrumented(SearchEmployeeInDirectory.class, employee);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        String fullName = employee.fullName();
        boolean found;

        cleanAndFocus(actor);
        typeLetterByLetter(actor, employee.getFirstName());
        found = isSuggestionVisible(actor, fullName);

        if (!found) {
            cleanAndFocus(actor);
            typeLetterByLetter(actor, employee.getLastName());
            found = isSuggestionVisible(actor, fullName);
        }

        if (found) {
            actor.attemptsTo(
                    Click.on(DirectoryPage.EMPLOYEE_NAME_SUGGESTION.of(fullName)),
                    WaitUntil.the(DirectoryPage.SEARCH_BUTTON, isClickable()),
                    Click.on(DirectoryPage.SEARCH_BUTTON),
                    WaitUntil.the(DirectoryPage.FIRST_RESULT_NAME, isVisible())
            );
        } else {
            throw new AssertionError("No se encontró la sugerencia para: " + fullName);
        }
    }

    private <T extends Actor> void typeLetterByLetter(T actor, String term) {
        for (char letter : term.toCharArray()) {
            actor.attemptsTo(
                    WaitUntil.the(DirectoryPage.EMPLOYEE_NAME_FILTER, isVisible()),
                    Enter.keyValues(String.valueOf(letter)).into(DirectoryPage.EMPLOYEE_NAME_FILTER)
            );
        }
    }

    private <T extends Actor> void cleanAndFocus(T actor) {
        actor.attemptsTo(
                WaitUntil.the(DirectoryPage.EMPLOYEE_NAME_FILTER, isVisible()),
                Click.on(DirectoryPage.EMPLOYEE_NAME_FILTER),
                Enter.keyValues(Keys.chord(Keys.CONTROL, "a")).into(DirectoryPage.EMPLOYEE_NAME_FILTER),
                Enter.keyValues(Keys.DELETE).into(DirectoryPage.EMPLOYEE_NAME_FILTER)
        );
    }

    private <T extends Actor> boolean isSuggestionVisible(T actor, String fullName) {
        try {
            actor.attemptsTo(
                    WaitUntil.the(DirectoryPage.EMPLOYEE_NAME_SUGGESTION.of(fullName), isVisible())
                            .forNoMoreThan(SUGGESTION_WAIT.toSeconds()).seconds()
            );
            return true;
        } catch (AssertionError | Exception e) {
            return false;
        }
    }
}