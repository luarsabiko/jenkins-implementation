Feature: Checkout Flow

  Background:
    Given I am logged in as standard user

  Scenario: Single item checkout
    When I add "Sauce Labs Backpack" to the cart
    Then the cart should contain "Sauce Labs Backpack"
    When I proceed to checkout
    Then I should see the success message "Thank you for your order!"

  Scenario: Multiple items checkout
    When I add "Sauce Labs Backpack" to the cart
    And I add "Sauce Labs Bike Light" to the cart
    Then the cart should contain "Sauce Labs Backpack" and "Sauce Labs Bike Light"
    When I proceed to checkout
    Then the subtotal should equal the sum of item prices
    And I should see the success message "Thank you for your order!"