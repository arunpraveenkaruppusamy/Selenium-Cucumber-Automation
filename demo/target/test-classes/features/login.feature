Feature: User Login Functionality

  As a user of the practice website,
  I want to log in successfully and be rejected when I use bad credentials,
  So that I can verify the security and access controls of the application.

  @smoke
  Scenario: Successful Login with Valid Credentials
    Given I am on the login page
    When I enter username "student" and password "Password123"
    And I click the login button
    Then I should be logged in successfully
    And I should see the welcome message "Congratulations!"
    
  @regression
  Scenario: Login Fails with Invalid Password
    Given I am on the login page
    When I enter username "student" and password "WrongPassword"
    And I click the login button
    Then I should not be logged in successfully
    And I should see the error message "Your username is invalid!"
    
  @regression
  Scenario: Login Fails with Empty Username and Password
    Given I am on the login page
    When I enter username "" and password ""
    And I click the login button
    Then I should not be logged in successfully
    And I should see the error message "Your username is invalid!"