package com.orangehrm.automation.tasks;

import com.orangehrm.automation.commons.Credentials;
import com.orangehrm.automation.ui.LoginPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class Login implements Task {

    private final Credentials credentials;

    public Login(Credentials credentials) {
        this.credentials = credentials;
    }

    public static Login with(Credentials credentials) {
        return instrumented(Login.class, credentials);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                WaitUntil.the(LoginPage.USERNAME, isVisible()),
                Enter.theValue(credentials.username()).into(LoginPage.USERNAME),
                Enter.theValue(credentials.password()).into(LoginPage.PASSWORD),
                Click.on(LoginPage.LOGIN_BUTTON)
        );
    }
}
