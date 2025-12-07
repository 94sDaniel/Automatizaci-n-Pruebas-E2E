package com.orangehrm.automation.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.screenplay.waits.WaitUntil;
import net.serenitybdd.core.Serenity;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static com.orangehrm.automation.ui.DashboardPage.PIM_MENU;
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
                    WaitUntil.the(PIM_MENU, isVisible()).forNoMoreThan(10).seconds(),
                    WaitUntil.the(PIM_MENU, isClickable()).forNoMoreThan(10).seconds(),
                    Click.on(PIM_MENU)
            );
        } catch (Throwable e) {
            System.out.println("⚠️ Click estándar falló. Intentando 'Force Click' con JS en el menú PIM.");

            WebDriver driver = Serenity.getWebdriverManager().getCurrentDriver();
            WebElement element = PIM_MENU.resolveFor(actor).getElement();

            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", element);
        }
    }
}