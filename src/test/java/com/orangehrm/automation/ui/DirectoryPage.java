package com.orangehrm.automation.ui;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class DirectoryPage {
    public static final Target EMPLOYEE_NAME_FILTER = Target.the("filtro de nombre de empleado")
            .located(By.xpath("//label[contains(normalize-space(),'Employee Name')]/following::input[1]"));
    public static final Target EMPLOYEE_NAME_SUGGESTIONS = Target.the("lista de sugerencias de nombres")
            .located(By.xpath("//div[@role='listbox' and contains(@class,'oxd-autocomplete-dropdown')]"));
    public static final Target EMPLOYEE_NAME_SUGGESTION = Target.the("sugerencia de empleado")
            .locatedBy("//div[@role='listbox']//div[@role='option']/span[contains(normalize-space(),'{0}')]");
    public static final Target SEARCH_BUTTON = Target.the("botón de búsqueda")
            .located(By.xpath("//button[normalize-space()='Search']"));
    public static final Target FIRST_RESULT_NAME = Target.the("nombre de empleado en resultados")
            .located(By.xpath("(//p[contains(@class,'oxd-text--p orangehrm-directory-card-header')])[1]"));
}
