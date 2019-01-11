Feature: Free CRM Login Feature

Scenario: Free CRM Login Test Scenario

Given user is already on login page
When title of login page is Free CRM
Then user enters username and password
Then user clicks on login button
Then user is on home page

Given user is on Contacts page
Then user clicks on New Contacts link
Then user fills the form and saves
Then close the browser