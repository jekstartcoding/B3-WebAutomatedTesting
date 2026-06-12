package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features/hasbi",
        glue = "stepdefinitions",
        plugin = {
                "pretty",
                "html:target/cucumber-report-hasbi.html",
                "json:target/cucumber-report-hasbi.json"
        },
        monochrome = true,
        publish = true
)
public class TestRunnerHasbi {
}
