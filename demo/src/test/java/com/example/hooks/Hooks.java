package com.example.hooks;

import com.example.utils.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;

public class Hooks {
    public static WebDriver driver;
    private final String BASE_URL = "https://practicetestautomation.com/practice-test-login/";

    @Before
    public void setup() {
        // Initialize the browser
        driver = DriverFactory.getDriver();
        // Navigate to the starting page
        driver.get(BASE_URL);
    }

    @After
    public void teardown() {
        // Close the browser
        DriverFactory.quitDriver();
    }
}