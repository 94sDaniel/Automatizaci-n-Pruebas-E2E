package com.orangehrm.automation.runners;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        plugin = {
                "pretty",
                "summary",
                "json:target/site/serenity/cucumber.json"
        },
        features = "src/test/resources/features",
        glue = "com.orangehrm.automation.steps",
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        monochrome = true
)
public class OrangeHrmTest {
}
