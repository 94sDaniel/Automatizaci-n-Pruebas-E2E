package com.orangehrm.automation.tasks;

import com.orangehrm.automation.ui.DashboardPage;
import com.orangehrm.automation.ui.PimPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.waits.WaitUntil;
import net.serenitybdd.core.Serenity;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isClickable;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;
import static net.serenitybdd.screenplay.Tasks.instrumented;

public class NavigateToPim implements Task {

    public static NavigateToPim module() {
        return instrumented(NavigateToPim.class);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        try {
            actor.attemptsTo(
                    WaitUntil.the(DashboardPage.PIM_MENU, isVisible()).forNoMoreThan(30).seconds(),
                    WaitUntil.the(DashboardPage.PIM_MENU, isClickable()).forNoMoreThan(30).seconds(),
                    Click.on(DashboardPage.PIM_MENU)
            );
        } catch (Throwable e) {
            System.out.println("Standard click failed, attempting JS click on PIM menu...");
            WebDriver driver = Serenity.getWebdriverManager().getCurrentDriver();
            WebElement element = DashboardPage.PIM_MENU.resolveFor(actor).getElement();
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
    }
}