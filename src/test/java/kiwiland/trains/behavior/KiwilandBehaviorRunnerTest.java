package kiwiland.trains.behavior;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(plugin = { "json:test-output/cucumber-report/Kiwiland.json", "html:test-output/cucumber-report/Kiwiland.html" }, glue = "kiwiland.trains.behavior", features = "src/test/resources/Behavior.feature")
public class KiwilandBehaviorRunnerTest extends AbstractTestNGCucumberTests {
    
}
