package com.example.runners;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/resources/features", // Path to your feature files
    glue = {"com.example.stepdefinitions", "com.example.hooks"}, // Path to your code
    tags = "@smoke or @regression", // Run scenarios tagged with either @smoke or @regression
    plugin = {"pretty", "html:target/cucumber-reports.html"}, // Plugins for readable output
    monochrome = true // makes the console output readable
)
public class TestRunner {
    // This class remains empty. The annotations drive Cucumber execution.
}