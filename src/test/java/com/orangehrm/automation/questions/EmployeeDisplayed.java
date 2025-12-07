package com.orangehrm.automation.questions;

import com.orangehrm.automation.ui.DirectoryPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;

public class EmployeeDisplayed implements Question<String> {

    public static EmployeeDisplayed name() {

        return new EmployeeDisplayed();
    }

    @Override
    public String answeredBy(Actor actor) {

        return Text.of(DirectoryPage.FIRST_RESULT_NAME).answeredBy(actor).trim();
    }
}
