package com.orangehrm.automation.ui;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class PimPage {
    public static final Target ADD_BUTTON = Target.the("botón de agregar empleado")
            .located(By.xpath("//button[normalize-space()='Add']"));
    public static final Target EMPLOYEE_LIST_TITLE = Target.the("título de la lista de empleados")
            .located(By.xpath("//h6[contains(normalize-space(),'Employee Information')]"));
}
