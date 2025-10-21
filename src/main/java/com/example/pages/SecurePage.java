package com.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions; // <-- ADD THIS IMPORT
import org.openqa.selenium.support.ui.WebDriverWait; // <-- ADD THIS IMPORT
import java.time.Duration; // <-- ADD THIS IMPORT

public class SecurePage {
    private WebDriver driver;
    // Instantiate WebDriverWait here for use in methods
    private WebDriverWait wait; // <-- ADD THIS LINE

    // Use a robust locator for the message on the success page
    @FindBy(css = "div.post-content p")
    private WebElement successMessage; 

    public SecurePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // <-- INITIALIZE WAIT
        PageFactory.initElements(driver, this);
    }

    // A method to retrieve the text
    public String getSuccessMessage() {
        // *** ADD THIS WAIT: Ensures the element is present after navigation ***
        wait.until(ExpectedConditions.visibilityOf(successMessage));
        return successMessage.getText();
    }
    
    // A quick check to confirm we are on the right page
    public boolean isLogoutButtonDisplayed() {
        return driver.getCurrentUrl().contains("logged-in-successfully");
    }
}