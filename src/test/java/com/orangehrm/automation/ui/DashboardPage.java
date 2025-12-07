package com.orangehrm.automation.ui;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class DashboardPage {
    public static final Target PIM_MENU = Target.the("opción de menú PIM")
            .located(By.xpath("//span[normalize-space()='PIM']"));
    public static final Target DIRECTORY_MENU = Target.the("opción de menú Directory")
            .located(By.xpath("//span[normalize-space()='Directory']"));
    public static final Target USER_MENU = Target.the("menú de usuario")
            .located(By.cssSelector(".oxd-userdropdown-name"));
    public static final Target DASHBOARD_TITLE = Target.the("Título del Dashboard")
            .located(By.xpath("//h6[normalize-space()='Dashboard']"));
}
