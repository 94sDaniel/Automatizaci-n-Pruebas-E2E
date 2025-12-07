Feature: OrangeHRM employee management
  As an OrangeHRM administrator
  I want to create and validate an employee
  So that the information is saved correctly and visible in the directory

  Background:
    Given "Agent" signs in to OrangeHRM

  Scenario: Create and validate employee via PIM and Directory
    When the user adds a new employee from the PIM module
      | firstName | lastName | employeeId | photo               |
      | Alexander | Yirsa    | 1000       | data/alex-photo.png |
    And the user searches the employee in the Directory module
    Then the user sees the newly created employee available in the directory
