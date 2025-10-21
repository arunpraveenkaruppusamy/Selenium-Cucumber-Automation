package com.example.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;

public class DriverFactory {
    // ThreadLocal ensures each thread gets its own WebDriver instance for parallel runs
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        if (driver.get() == null) {
            // NOTE: You must have the appropriate chromedriver executable set up.
            // A more modern approach uses WebDriverManager dependency. 
            // For simplicity here, assume the driver is in your PATH or explicitly set:
            // System.setProperty("webdriver.chrome.driver", "/path/to/your/chromedriver");
            WebDriver localDriver = new ChromeDriver();
        localDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            localDriver.manage().window().maximize();
            driver.set(localDriver);
        }
        return driver.get();
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}