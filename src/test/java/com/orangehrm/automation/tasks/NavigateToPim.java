package com.orangehrm.automation.tasks;

import com.orangehrm.automation.ui.DashboardPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isClickable;

public class NavigateToPim implements Task {

    public static NavigateToPim module() {
        return instrumented(NavigateToPim.class);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                WaitUntil.the(DashboardPage.PIM_MENU, isClickable()),
                Click.on(DashboardPage.PIM_MENU)
        );
    }
}
