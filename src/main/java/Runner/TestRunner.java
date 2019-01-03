package Runner;

import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "C:/Users/nagaprakhya_w/eclipse-workspace/FreeCrmBDDFramework/src/main/java/Features/login.feature", 
		glue = {"stepDefinition"}
		)


public class TestRunner {

}
