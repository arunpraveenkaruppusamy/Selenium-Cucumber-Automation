package com.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private final WebDriver driver;

    // 1. Locators (The 'where' on the page)
    private final By usernameField = By.id("username");
    private final By passwordField = By.id("password");
    private final By submitButton = By.id("submit");
    private final By successMessage = By.className("post-title");
    private final By errorMessage = By.id("error");

    // Constructor to receive the driver
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    // 2. Actions (The 'what' we do on the page)
    public void enterCredentials(String username, String password) {
        // Find element and type
        driver.findElement(usernameField).sendKeys(username);
        driver.findElement(passwordField).sendKeys(password);
    }

    public void clickSubmit() {
        driver.findElement(submitButton).click();
    }

    // 3. Verification/Getters (The 'result')
    public String getSuccessMessageText() {
        return driver.findElement(successMessage).getText();
    }

    public String getErrorMessageText() {
        return driver.findElement(errorMessage).getText();
    }
}