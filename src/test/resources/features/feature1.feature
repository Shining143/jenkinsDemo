Feature: facebook

  Scenario: title test
    Given open chrome browser
    When launch site "https://www.facebook.com"
    Then title should be "Facebook"
    When close site 