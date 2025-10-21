Feature: Login Functionality on Practice Test Automation Site

  Scenario Outline: Verify Login with various credentials from Excel
    Given the user is on the login page
    When the user enters credentials for scenario "<scenario_name>"
    And clicks the login button
    Then the user should see the message "<expected_result_text>" on the page

    Examples:
      # The scenario_name will be used to look up the actual username/password in Excel
      # The expected_result_text will be asserted in the Step Definition
      | scenario_name            | expected_result_text                         |
      | Successful Login         | Congratulations student. You successfully logged in! |
      | Invalid Username Login   | Your username is invalid!                    |
      | Invalid Password Login   | Your password is invalid!                    | 