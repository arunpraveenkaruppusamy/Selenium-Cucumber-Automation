package com.example.hooks;

import com.example.utils.ConfigFileReader; // <--- 🌟 Import the Config Reader
import com.example.utils.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriver;

public class Hooks {

    private static WebDriver driver; 
    
    // 💡 CHANGE 2: Replace the hard-coded BROWSER with a call to the ConfigFileReader
    // We create the reader object once for performance
    private static final ConfigFileReader configReader = new ConfigFileReader();
    
    // Optional: Make the browser name easily available if needed
    private static final String BROWSER_NAME = configReader.getBrowser();
    
    @Before
    public void setup(Scenario scenario) {
        System.out.println("Starting scenario: " + scenario.getName());
        
        // Initialize the driver using the browser name from the config file!
        driver = DriverFactory.getWebDriver(BROWSER_NAME); // <--- Use BROWSER_NAME here
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