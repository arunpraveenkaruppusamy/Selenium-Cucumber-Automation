package com.example.stepdefenitions;

import com.example.pages.LoginPage;
import com.example.pages.SecurePage;
import com.example.utils.ExcelReader;
import com.example.hooks.Hooks;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import org.junit.Assert;
import java.util.Map;

public class LoginSteps {

    // Get the WebDriver instance managed by the Hooks
    private WebDriver driver = Hooks.getDriver(); 
    private LoginPage loginPage = new LoginPage(driver);
    private Map<String, String> testData;

    @Given("the user is on the login page")
    public void theUserIsOnTheLoginPage() {
        loginPage.navigateToLoginPage();
    }

    // Step to read data from Excel based on the scenario name from the Feature file
    @When("the user enters credentials for scenario {string}")
    public void theUserEntersCredentialsForScenario(String scenarioName) {
        // 1. Get the data row from the Excel file
        testData = ExcelReader.getTestData(scenarioName);
        
        // Basic check to ensure data was found
        if (testData.isEmpty()) {
            Assert.fail("Test data not found in Excel for scenario: " + scenarioName);
        }

        // 2. Extract credentials and perform action
        String username = testData.get("username");
        String password = testData.get("password");

        loginPage.enterCredentials(username, password);
        System.out.println("Entering U/P for scenario: " + scenarioName);
    }

    @When("clicks the login button")
    public void clicksTheLoginButton() {
        loginPage.clickLoginButton();
    }

    // Inside com.example.stepdefenitions.LoginSteps.java
@Then("the user should see the message {string} on the page")
public void theUserShouldSeeTheMessageOnThePage(String expectedMessage) {
    if (expectedMessage.contains("successfully logged in")) {
        // 1. Create the Secure Page object
        SecurePage securePage = new SecurePage(driver);
        
        // 2. Validate the navigation first (good practice!)
        Assert.assertTrue("Failed to navigate to the Logged In page.", securePage.isLogoutButtonDisplayed());
        // 3. Get the message from the Secure Page and assert
        String actualMessage = securePage.getSuccessMessage();

        Assert.assertTrue("The success message was incorrect. Expected: '" + expectedMessage + "', Actual: '" + actualMessage + "'", actualMessage.contains(expectedMessage));
    } else {
        // Error case: Check for the message on the Login Page (as before)
        String actualMessage = loginPage.getErrorMessage(); // Use your existing loginPage object
       Assert.assertTrue("The error message was incorrect. Expected: '" + expectedMessage + "' Actual: '" + actualMessage + "'", actualMessage.contains(expectedMessage));
    }
}
}