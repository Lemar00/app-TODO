package cucumber.steps;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src\\test\\resources\\features", 
        glue = "cucumber.steps" 
)
public class CucumberTestRun {
    
}
