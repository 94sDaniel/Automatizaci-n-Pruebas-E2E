package com.orangehrm.automation.tasks;

import com.orangehrm.automation.ui.DashboardPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isClickable;

public class NavigateToDirectory implements Task {

    public static NavigateToDirectory module() {
        return instrumented(NavigateToDirectory.class);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                WaitUntil.the(DashboardPage.DIRECTORY_MENU, isClickable()),
                Click.on(DashboardPage.DIRECTORY_MENU)
        );
    }
}
