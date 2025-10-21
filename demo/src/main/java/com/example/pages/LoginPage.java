package com.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class LoginPage {
    private WebDriver driver;
    private final String LOGIN_URL = "https://practicetestautomation.com/practice-test-login/";

    // Locators
    private By usernameField = By.id("username");
    private By passwordField = By.id("password");
    private By loginButton = By.id("submit");
    private By messageText = By.id("error"); // This ID holds the failure message

    // Constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    // Actions (Methods referenced in LoginSteps)
    public void navigateToLoginPage() {
        driver.get(LOGIN_URL);
    }

    public void enterCredentials(String username, String password) {
        driver.findElement(usernameField).sendKeys(username);
        driver.findElement(passwordField).sendKeys(password);
    }

    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    // Synchronized method to get the message text
    public String getMessageText() {
        // Wait up to 10 seconds until the message text element is present and visible
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(messageText));
        
        return driver.findElement(messageText).getText();
    }
}