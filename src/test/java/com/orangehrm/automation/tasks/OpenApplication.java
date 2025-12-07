package com.orangehrm.automation.tasks;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Open;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class OpenApplication implements Task {

    public static OpenApplication atLoginPage() {
        return instrumented(OpenApplication.class);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        Config config = ConfigFactory.load();
        String baseUrl;

        if (config.hasPath("base.url")) {
            baseUrl = config.getString("base.url");
        } else {
            baseUrl = "https://opensource-demo.orangehrmlive.com";
        }
        actor.attemptsTo(Open.url(baseUrl + "/web/index.php/auth/login"));
    }
}