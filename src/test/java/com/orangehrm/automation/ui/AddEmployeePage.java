package com.orangehrm.automation.ui;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class AddEmployeePage {
    public static final Target FIRST_NAME = Target.the("nombre del empleado")
            .located(By.name("firstName"));
    public static final Target LAST_NAME = Target.the("apellido del empleado")
            .located(By.name("lastName"));
    public static final Target EMPLOYEE_ID = Target.the("identificador del empleado")
            .located(By.xpath("//label[contains(normalize-space(),'Employee Id')]/../following-sibling::div//input"));
    public static final Target PHOTO = Target.the("fotografía del empleado")
            .located(By.xpath("//input[@type='file']"));
    public static final Target SAVE_BUTTON = Target.the("botón para guardar empleado")
            .located(By.xpath("//button[normalize-space()='Save']"));
    public static final Target PERSONAL_DETAILS_HEADER = Target.the("encabezado de detalles personales")
            .located(By.xpath("//h6[contains(normalize-space(),'Personal Details')]"));
    public static final Target SUCCESS_TOAST = Target.the("notificación de guardado exitoso")
            .located(By.xpath("//p[contains(normalize-space(),'Successfully Saved')]"));
    public static final Target EMPLOYEE_ID_ERROR = Target.the("mensaje de id de empleado duplicado")
            .located(By.xpath("//span[contains(normalize-space(),'Employee Id already exists')]"));
}
