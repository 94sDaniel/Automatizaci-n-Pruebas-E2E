package com.orangehrm.automation.ui;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class LoginPage {
    public static final Target USERNAME = Target.the("campo de usuario").located(By.name("username"));
    public static final Target PASSWORD = Target.the("campo de contraseña").located(By.name("password"));
    public static final Target LOGIN_BUTTON = Target.the("botón de inicio de sesión")
            .located(By.cssSelector("button[type='submit']"));
}
