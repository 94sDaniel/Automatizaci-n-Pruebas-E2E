package com.orangehrm.automation.commons;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.thucydides.core.webdriver.ThucydidesWebDriverSupport;
import org.openqa.selenium.WebDriver;

public final class ActorFactory {

    private ActorFactory() {
    }

    public static Actor withBrowser(String actorName, WebDriver driver) {
        WebDriver managedDriver = driver != null ? driver : ThucydidesWebDriverSupport.getDriver();
        Actor actor = Actor.named(actorName);
        actor.can(BrowseTheWeb.with(managedDriver));
        return actor;
    }
}
