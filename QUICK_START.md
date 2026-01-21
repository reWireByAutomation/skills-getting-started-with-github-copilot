# Mobile Automation Framework - Quick Start Guide

## Prerequisites Setup

### 1. Install Java 11+
```bash
java -version
```

### 2. Install Node.js and Appium
```bash
npm install -g appium
appium driver install uiautomator2  # For Android
appium driver install xcuitest      # For iOS (macOS only)
```

### 3. Start Appium Server
```bash
appium
# Server will start at http://localhost:4723
```

## Quick Start

### 1. Configure Your App
Edit `src/test/resources/config/config.properties`:

```properties
# Choose platform: android or ios
platform.type=android

# Android Configuration
android.app.path=/path/to/your/app.apk
android.device.name=Android Emulator
android.app.package=com.yourapp
android.app.activity=.MainActivity

# iOS Configuration (macOS only)
ios.app.path=/path/to/your/app.app
ios.device.name=iPhone 14
ios.bundle.id=com.yourapp
```

### 2. Build the Project
```bash
gradle clean build -x test
```

### 3. Run Tests
```bash
# Run all tests
gradle test

# Run with specific tags
gradle test -Dcucumber.filter.tags="@smoke"
```

## Writing Your First Test

### Step 1: Create Feature File
Create `src/test/resources/features/MyFeature.feature`:

```gherkin
Feature: My Feature
  @smoke
  Scenario: My first test
    Given I am on the home screen
    When I tap the login button
    Then I should see the login form
```

### Step 2: Create Page Object
Create `src/main/java/com/automation/pages/HomePage.java`:

```java
public class HomePage extends BasePage {
    @AndroidFindBy(id = "com.app:id/login_btn")
    @iOSXCUITFindBy(id = "loginButton")
    private WebElement loginButton;
    
    public void tapLoginButton() {
        click(loginButton, "Login Button");
    }
}
```

### Step 3: Create Step Definitions
Create `src/test/java/com/automation/stepdefinitions/MySteps.java`:

```java
public class MySteps {
    private HomePage homePage;
    
    @Given("I am on the home screen")
    public void iAmOnTheHomeScreen() {
        homePage = new HomePage();
    }
    
    @When("I tap the login button")
    public void iTapTheLoginButton() {
        homePage.tapLoginButton();
    }
}
```

## Viewing Reports

After test execution, open:
- **Extent Report**: `extent-reports/Spark.html`
- **Cucumber Report**: `cucumber-reports/cucumber.html`
- **Logs**: `logs/automation.log`
- **Screenshots**: `screenshots/` (on failures)

## Common Commands

```bash
# Clean and build
gradle clean build

# Run tests without building
gradle test

# Run specific test tags
gradle test -Dcucumber.filter.tags="@smoke"
gradle test -Dcucumber.filter.tags="@regression"

# Run with custom config
gradle test -Dconfig.file=/path/to/config.properties
```

## Tips

1. **Start Appium before running tests**
2. **Use explicit waits** instead of Thread.sleep()
3. **Tag your scenarios** (@smoke, @regression, etc.)
4. **Check logs** for detailed execution flow
5. **Screenshots** are auto-captured on failures

## Troubleshooting

**Problem**: Driver initialization fails
- Verify Appium server is running
- Check app path in config.properties
- Ensure device/emulator is running

**Problem**: Element not found
- Check element locators in page objects
- Increase wait timeouts in config.properties
- Verify app is on the correct screen

**Problem**: Build fails
- Run `gradle clean build`
- Check Java version (needs 11+)
- Verify all dependencies downloaded

## Support

For detailed information, see [MOBILE_AUTOMATION_FRAMEWORK.md](MOBILE_AUTOMATION_FRAMEWORK.md)
