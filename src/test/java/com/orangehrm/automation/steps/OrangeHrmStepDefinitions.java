package com.orangehrm.automation.steps;

import com.orangehrm.automation.commons.ActorFactory;
import com.orangehrm.automation.commons.CredentialProvider;
import com.orangehrm.automation.commons.EmployeeData;
import com.orangehrm.automation.questions.EmployeeDisplayed;
import com.orangehrm.automation.tasks.AddEmployee;
import com.orangehrm.automation.tasks.Login;
import com.orangehrm.automation.tasks.NavigateToDirectory;
import com.orangehrm.automation.tasks.NavigateToPim;
import com.orangehrm.automation.tasks.OpenApplication;
import com.orangehrm.automation.tasks.SearchEmployeeInDirectory;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.screenplay.ensure.Ensure;
import net.serenitybdd.annotations.Managed;
import org.openqa.selenium.WebDriver;

import java.util.List;
import java.util.Map;

public class OrangeHrmStepDefinitions {

    @Managed
    WebDriver driver;

    private Actor actor;
    private EmployeeData employeeData;

    @Before
    public void setTheStage() {

        OnStage.setTheStage(new OnlineCast());
    }

    @Given("{string} signs in to OrangeHRM")
    public void actorSignsIn(String actorName) {
        actor = ActorFactory.withBrowser(actorName, driver);
        actor.wasAbleTo(
                OpenApplication.atLoginPage(),
                Login.with(CredentialProvider.adminCredentials()),
                NavigateToPim.module()
        );
    }

    @When("the user adds a new employee from the PIM module")
    public void addNewEmployeeFromPim(DataTable table) {
        List<Map<String, String>> data = table.asMaps();
        Map<String, String> row = data.get(0);
        employeeData = new EmployeeData(
                row.get("firstName"),
                row.get("lastName"),
                row.get("employeeId"),
                row.get("photo")
        );
        actor.attemptsTo(
                AddEmployee.withData(employeeData)
        );
    }

    @When("the user searches the employee in the Directory module")
    public void searchEmployeeInDirectory() {
        actor.attemptsTo(
                NavigateToDirectory.module(),
                SearchEmployeeInDirectory.byName(employeeData)
        );
    }

    @Then("the user sees the newly created employee available in the directory")
    public void validateEmployeeIsAvailableInDirectory() {
        actor.attemptsTo(
                Ensure.that(EmployeeDisplayed.name()).containsIgnoringCase(employeeData.fullName())
        );
    }
}
