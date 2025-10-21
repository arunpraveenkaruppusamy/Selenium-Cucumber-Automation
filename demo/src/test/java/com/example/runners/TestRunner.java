// Fix your TestRunner.java to look like this:
package com.example.runners;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        // The fix is here: change 'src/main' to 'src/test'
        features = "src/test/resources/features", 
        glue = {"com.example.stepdefenitions", "com.example.hooks"}, 
        plugin = {"pretty", "html:target/cucumber-reports.html"}, 
        monochrome = true, 
        tags = "" 
)
public class TestRunner {
    // ...
}