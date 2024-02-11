Feature: Gorest CRUD operation check
  I should be able to check CRUD operation on the website

  Scenario Outline:Verify CRUD operation on Gorest application
    Given Gorest application is running
    When  I create a new user providing name "<name>" email "<email>" gender "<gender>" status "<status>"
    Then  I verify that the user is created
    And   I update the user with name "<name1>" email "<email1>" gender "<gender>" status "<status>"
    Then  I verify that the user with updated name "<name1>" is updated successfully
    When  I delete the user with name "<name>"
    Then  I verify that the user with name "<name>" is deleted successfully
    Examples:
      | name      |  email           | gender          | status    |name1           |email1                    |
      | Prime1    | prime1@gmail.com | female          | Active    |Primeupdated011 |prime11updated@gmail.com |
      | Prime2    | prime2@gmail.com | male            | Inactive  |Primeupdated012 |prime12updated@gmail.com |