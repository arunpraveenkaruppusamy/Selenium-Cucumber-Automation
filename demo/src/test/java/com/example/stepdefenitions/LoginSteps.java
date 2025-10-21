package com.example.stepdefenitions;

import com.example.hooks.Hooks;
import com.example.pages.LoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.junit.Assert;

public class LoginSteps {
    // The driver is shared from the Hooks class
    private final LoginPage loginPage = new LoginPage(Hooks.driver);

    @Given("I am on the login page")
    public void i_am_on_the_login_page() {
        // Already handled by the @Before hook navigating to the base URL
        // We can add a simple check here if needed, but it's usually cleaner
        // to leave navigation to Hooks/DriverFactory.
        System.out.println("Navigated to the login page successfully.");
    }

    @When("I enter username {string} and password {string}")
    public void i_enter_username_and_password(String username, String password) {
        loginPage.enterCredentials(username, password);
    }

    @When("I click the login button")
    public void i_click_the_login_button() {
        loginPage.clickSubmit();
    }

    @Then("I should be logged in successfully")
    public void i_should_be_logged_in_successfully() {
        // Assert that the URL changed or a success element is present
        String currentUrl = Hooks.driver.getCurrentUrl();
        // Quick check to see if we've been redirected to the logged-in page
        Assert.assertFalse("Login was not successful, still on login page.", 
                           currentUrl.contains("login"));
    }
    
    @Then("I should not be logged in successfully")
    public void i_should_not_be_logged_in_successfully() {
        // Assert that we are still on the login URL
        String currentUrl = Hooks.driver.getCurrentUrl();
        Assert.assertTrue("Unexpectedly logged in/redirected.", 
                          currentUrl.contains("login"));
    }

    @Then("I should see the welcome message {string}")
    public void i_should_see_the_welcome_message(String expectedMessage) {
        String actualMessage = loginPage.getSuccessMessageText();
        Assert.assertTrue("Welcome message mismatch. Expected: " + expectedMessage + 
                          " Actual: " + actualMessage, 
                          actualMessage.contains(expectedMessage));
    }

    @Then("I should see the error message {string}")
    public void i_should_see_the_error_message(String expectedError) {
        String actualError = loginPage.getErrorMessageText();
        Assert.assertTrue("Error message mismatch. Expected: " + expectedError + 
                          " Actual: " + actualError, 
                          actualError.contains(expectedError));
    }
}