# SauceDemo E2E Test Automation Framework

End-to-end test automation framework for [saucedemo.com](https://www.saucedemo.com) built with Selenium WebDriver, TestNG, Cucumber BDD, and Allure Reporting.

---

## Tech Stack

| Tool | Purpose |
|------|---------|
| Java 17 | Language |
| Selenium WebDriver 4.41.0 | Browser automation |
| TestNG 7.9.0 | Test runner |
| Cucumber 7.15.0 | BDD framework |
| Hamcrest 2.2 | Assertions |
| Allure 2.24.0 | Test reporting |
| Maven | Build tool |

---

## Project Structure

```
src/test/java/com/epam/ta/
├── cucumber-hooks/
│   └── Hooks.java                 # Before/After scenario hooks
├── driver/
│   └── DriverSingleton.java       # WebDriver singleton (Chrome/Firefox)
├── model/
│   ├── User.java                  # User domain model
│   └── CheckoutData.java          # Checkout form data model
├── page/
│   ├── AbstractPage.java          # Base page class
│   ├── LoginPage.java
│   ├── InventoryPage.java
│   ├── CartPage.java
│   ├── CheckoutInfoPage.java
│   ├── CheckoutOverviewPage.java
│   └── CheckoutCompletePage.java
├── runner/
│   ├── CucumberRunner.java        # Cucumber TestNG runner
├── service/
│   ├── TestDataReader.java        # Reads data from .properties files
│   ├── UserCreator.java           # Creates User instances
│   └── CheckoutDataCreator.java   # Creates CheckoutData instances
├── steps/
│   └── CheckoutSteps.java         # Cucumber step definitions
└── util/
    └── StringUtils.java           # String utility methods

src/test/resources/
├── dev.properties                 # Test data for dev environment
├── testng-smoke.xml               # Test suite configuration
└── features/
    └── checkout-flow.feature      # BDD feature file
```

---

## Prerequisites

- Java 17+
- Maven 3.8+
- Chrome or Firefox installed on your machine
- Allure CLI (for report generation — see below)

---

## Installation

### 1. Clone the repository

```bash
git clone <repository-url>
cd final-task
```

### 2. Install Allure CLI

**Windows (using Scoop):**

```powershell
# Install Scoop if you don't have it
irm get.scoop.sh | iex

# Then install Allure
scoop install allure
```

Verify installation:
```bash
allure --version
```

---

## Running Tests

### Run on Chrome (default)
```bash
mvn -Dbrowser=chrome -Denvironment=dev clean test
```

### Run on Firefox
```bash
mvn -Dbrowser=firefox -Denvironment=dev clean test
```

---

## Test Scenarios

Tests are written in Gherkin BDD format in `src/test/resources/features/checkout-flow.feature`.

### UC-1 — Single Item Checkout
```gherkin
Scenario: Single item checkout
  Given I am logged in as standard user
  When I add "Sauce Labs Backpack" to the cart
  Then the cart should contain "Sauce Labs Backpack"
  When I proceed to checkout
  Then I should see the success message "Thank you for your order!"
```

### UC-2 — Multiple Items Checkout
```gherkin
Scenario: Multiple items checkout
  Given I am logged in as standard user
  When I add "Sauce Labs Backpack" to the cart
  And I add "Sauce Labs Bike Light" to the cart
  Then the cart should contain "Sauce Labs Backpack" and "Sauce Labs Bike Light"
  When I proceed to checkout
  Then the subtotal should equal the sum of item prices
  And I should see the success message "Thank you for your order!"
```


Product names are parametrized directly in the Gherkin steps using quoted strings — no code changes needed. To test with different products simply update the product names in the feature file:

```gherkin
# Single product - change this value
When I add "Sauce Labs Fleece Jacket" to the cart
 
# Multiple products - change either or both values
When I add "Sauce Labs Fleece Jacket" to the cart
And I add "Sauce Labs Onesie" to the cart
```

Cucumber passes the quoted string values directly to the step definitions at runtime.

---

## Configuration

### Environment properties (`src/test/resources/dev.properties`)

```properties
user.login=standard_user
user.password=secret_sauce
checkout.firstname=Saba
checkout.lastname=Beridze
checkout.zipcode=12345
```

### Test suite (`src/test/resources/testng-smoke.xml`)

```xml
<suite name="Smoke" verbose="1">
    <test name="CucumberBDD">
        <classes>
            <class name="com.epam.ta.runner.CucumberRunner"/>
        </classes>
    </test>
</suite>
```

---

## Allure Report

### Generate and open report (one command)
```bash
allure serve target/allure-results
```

### Generate report to disk
```bash
allure generate target/allure-results --clean -o target/allure-report
```

### Open saved report
```bash
allure open target/allure-report
```

### Reading the Allure Report

Once the report opens in your browser:

- **Overview** — summary of test run: total, passed, failed, broken, skipped
- **Behaviors** — scenarios organized by feature file
- **Suites** — tests organized by runner and scenario name
- **Timeline** — shows test execution time and order
- **Test details** — click any scenario to see step-by-step execution log with Gherkin steps

---

## Author

Saba Beridze