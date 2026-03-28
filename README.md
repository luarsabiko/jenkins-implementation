# SauceDemo E2E Test Automation Framework

End-to-end test automation framework for [saucedemo.com](https://www.saucedemo.com) built with Selenium WebDriver, TestNG, and Allure Reporting.

---

## Tech Stack

| Tool | Purpose |
|------|---------|
| Java 17 | Language |
| Selenium WebDriver 4.41.0 | Browser automation |
| TestNG 7.9.0 | Test runner |
| Hamcrest 2.2 | Assertions |
| Allure 2.24.0 | Test reporting |
| SLF4J | Logging |
| Maven | Build tool |

---

## Project Structure

```
src/test/java/com/epam/ta/
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
├── service/
│   ├── TestDataReader.java        # Reads data from .properties files
│   ├── UserCreator.java           # Creates User instances
│   └── CheckoutDataCreator.java   # Creates CheckoutData instances
├── test/
│   ├── CommonConditions.java      # Base test class (setUp/tearDown)
│   └── CheckoutTests.java         # Test cases
└── util/
    └── StringUtils.java           # String utility methods

src/test/resources/
├── dev.properties                 # Test data for dev environment
├── testng-smoke.xml               # Smoke test suite
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
cd FinalTask
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

### Run smoke tests on Chrome (default)
```bash
mvn -Dbrowser=chrome -Denvironment=dev clean test
```

### Run smoke tests on Firefox
```bash
mvn -Dbrowser=firefox -Denvironment=dev clean test
```

## Test Cases

| Test | Description |
|------|-------------|
| `checkoutFlowOneItem` | Login, add one product to cart, complete checkout, validate success message |
| `checkoutFlowSeveralItems` | Login, add two products to cart, validate prices match subtotal, complete checkout |

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
- **Suites** — tests organized by class and method name
- **Behaviors** — tests organized by Epic → Story (e.g. Checkout Flow → UC-1)
- **Timeline** — shows test execution time and order
- **Test details** — click any test to see step-by-step execution log, severity, description, and any attachments

---

## TestNG Report

TestNG generates an HTML report automatically after every test run.

### Location
```
target/surefire-reports/index.html
```

### Opening the report
Open the file directly in your browser:
```bash
start target/surefire-reports/emailable-report.html
```

### Reading the TestNG Report

- **Overview** — shows total passed, failed, skipped counts
- **All tests** — lists every test method with execution time and status
- **Failed tests** — shows stack trace and failure reason for each failed test
- **Chronological view** — shows tests in execution order

---

## Configuration

### Environment properties (`src/test/resources/dev.properties`)

```properties
user.login=standard_user
user.password=secret_sauce
checkout.firstname=Saba
checkout.lastname=Beridze
checkout.zipcode=12345
checkout.success.message=Thank you for your order!
```

### Test parameters (`src/test/resources/testng-smoke.xml`)

```xml
<parameter name="productName" value="Sauce Labs Backpack"/>
<parameter name="productName2" value="Sauce Labs Bike Light"/>
```

To run with different products, update these values in `testng-smoke.xml`.

---

## Author

Saba Beridze