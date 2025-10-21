package com.example.hooks;

import com.example.utils.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriver;

public class Hooks {

    // ThreadLocal is best practice for parallel execution, but for now, static is fine.
    private static WebDriver driver; 
    private static final String BROWSER = "chrome"; // Can be read from a config file later

    @Before
    public void setup(Scenario scenario) {
        System.out.println("Starting scenario: " + scenario.getName());
        
        // Initialize the driver using the factory
        driver = DriverFactory.getWebDriver(BROWSER);
    }

    @After
    public void tearDown(Scenario scenario) {
        
        // Optional: Add screenshot on failure logic here later
        if (scenario.isFailed()) {
             System.err.println("Scenario failed: " + scenario.getName());
        }

        // Close the browser
        if (driver != null) {
            driver.quit();
            System.out.println("Browser closed.");
        }
    }
    
    // Static method to allow Step Definitions to access the driver
    public static WebDriver getDriver() {
        return driver;
    }
}