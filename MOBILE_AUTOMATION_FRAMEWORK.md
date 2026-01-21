# Mobile Apps Automation Framework

A robust, scalable, and reusable framework for mobile application automation testing supporting both Android and iOS platforms.

## Framework Stack

- **Language**: Java 11
- **Mobile Automation**: Appium Java Client 9.1.0
- **BDD Framework**: Cucumber 7.14.0 with Gherkin
- **Build Tool**: Gradle
- **Test Framework**: TestNG 7.8.0
- **Logging**: SLF4J 2.0.9 with Logback
- **Reporting**: 
  - Cucumber HTML Reports
  - Extent Reports 5.1.1
  - ExtentReports Cucumber Adapter

## Features

✅ **Cross-Platform Support**: Single framework for both Android and iOS automation
✅ **BDD Implementation**: Write tests in natural Gherkin language
✅ **Page Object Model**: Maintainable and reusable page objects
✅ **Comprehensive Logging**: SLF4J logging with granular event tracking
✅ **Rich Reporting**: Multiple reporting formats including Extent Reports
✅ **Screenshot Capture**: Automatic screenshot capture on test failures
✅ **Configurable**: Easy configuration management through properties files
✅ **Parallel Execution**: Support for parallel test execution (configurable)

## Project Structure

```
mobile-automation-framework/
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com/
│   │           └── automation/
│   │               ├── config/          # Configuration management
│   │               ├── drivers/         # Driver initialization and management
│   │               ├── pages/           # Page Object Model classes
│   │               └── utils/           # Utility classes
│   └── test/
│       ├── java/
│       │   └── com/
│       │       └── automation/
│       │           ├── runners/         # TestNG Cucumber runners
│       │           └── stepdefinitions/ # Cucumber step definitions
│       └── resources/
│           ├── config/                  # Configuration files
│           ├── features/                # Gherkin feature files
│           ├── extent.properties        # Extent Reports config
│           ├── extent-config.xml        # Extent Reports theme config
│           ├── logback.xml              # Logging configuration
│           └── testng.xml               # TestNG suite configuration
├── build.gradle                         # Gradle build configuration
├── settings.gradle                      # Gradle settings
└── gradle.properties                    # Gradle properties
```

## Prerequisites

1. **Java Development Kit (JDK) 11+**
   ```bash
   java -version
   ```

2. **Gradle** (or use Gradle wrapper)
   ```bash
   gradle --version
   ```

3. **Node.js and Appium**
   ```bash
   npm install -g appium
   appium --version
   ```

4. **Appium Drivers**
   ```bash
   # For Android
   appium driver install uiautomator2
   
   # For iOS
   appium driver install xcuitest
   ```

5. **Android SDK** (for Android testing)
   - Android Studio with SDK tools
   - Environment variables: ANDROID_HOME

6. **Xcode** (for iOS testing - macOS only)
   - Xcode Command Line Tools
   - iOS Simulator

## Setup Instructions

### 1. Clone the Repository
```bash
git clone <repository-url>
cd mobile-automation-framework
```

### 2. Configure Test Settings

Edit `src/test/resources/config/config.properties`:

```properties
# Set platform type
platform.type=android  # or ios

# Update Appium server URL if needed
appium.server.url=http://localhost:4723/wd/hub

# Android Configuration
android.app.path=path/to/your/app.apk
android.device.name=Android Emulator
android.platform.version=13.0
android.app.package=com.example.app
android.app.activity=.MainActivity

# iOS Configuration
ios.app.path=path/to/your/app.app
ios.device.name=iPhone 14
ios.platform.version=16.0
ios.bundle.id=com.example.app
```

### 3. Start Appium Server
```bash
appium
```

### 4. Build the Project
```bash
gradle clean build
```

## Running Tests

### Run All Tests
```bash
gradle test
```

### Run Cucumber Tests
```bash
gradle cucumberTest
```

### Run with Specific Tags
Edit `src/test/java/com/automation/runners/TestRunner.java`:
```java
@CucumberOptions(
    tags = "@smoke"  // Run only smoke tests
)
```

### Run from Command Line with Tags
```bash
gradle test -Dcucumber.filter.tags="@smoke"
```

## Writing Tests

### 1. Create Feature File
Create a `.feature` file in `src/test/resources/features/`:

```gherkin
Feature: User Login
  As a user
  I want to login to the app
  So that I can access my account

  @smoke @login
  Scenario: Successful login
    Given I am on the login screen
    When I enter username "user@example.com"
    And I enter password "password123"
    And I click on login button
    Then I should see the home screen
```

### 2. Create Step Definitions
Create step definition class in `src/test/java/com/automation/stepdefinitions/`:

```java
public class LoginSteps {
    private LoginPage loginPage;
    
    @Given("I am on the login screen")
    public void iAmOnTheLoginScreen() {
        loginPage = new LoginPage();
    }
    
    @When("I enter username {string}")
    public void iEnterUsername(String username) {
        loginPage.enterUsername(username);
    }
}
```

### 3. Create Page Objects
Create page object class in `src/main/java/com/automation/pages/`:

**For Android:**
```java
public class LoginPage extends BasePage {
    @AndroidFindBy(id = "com.app:id/username")
    private WebElement usernameField;
    
    public void enterUsername(String username) {
        enterText(usernameField, username, "Username Field");
    }
}
```

**For iOS:**
```java
public class LoginPage extends BasePage {
    @iOSXCUITFindBy(id = "username")
    private WebElement usernameField;
    
    public void enterUsername(String username) {
        enterText(usernameField, username, "Username Field");
    }
}
```

## Reports

### Cucumber HTML Report
- Location: `cucumber-reports/cucumber.html`
- Open in browser after test execution

### Extent Reports
- Location: `extent-reports/Spark.html`
- Rich interactive HTML report with charts and graphs

### Logs
- Console logs with timestamps
- File logs: `logs/automation.log`
- Daily rolling file appender (30-day retention)

### Screenshots
- Automatically captured on test failures
- Location: `screenshots/`
- Named with scenario name and timestamp

## Configuration Files

### config.properties
Main configuration for:
- Platform selection (Android/iOS)
- Appium server settings
- Device capabilities
- App paths
- Timeouts

### logback.xml
Logging configuration:
- Log levels
- Log patterns
- File appenders
- Console output

### testng.xml
TestNG suite configuration:
- Test classes
- Parallel execution settings
- Test parameters

### extent.properties
Extent Reports configuration:
- Report paths
- Screenshot settings
- Report themes

## Best Practices

1. **Page Object Model**: Always use POM pattern for maintainability
2. **Logging**: Add meaningful logs at every step using SLF4J
3. **Waits**: Use explicit waits instead of hard-coded sleeps
4. **Assertions**: Use TestNG assertions in step definitions
5. **Naming**: Use descriptive names for scenarios, steps, and page objects
6. **Tags**: Tag scenarios appropriately (@smoke, @regression, etc.)
7. **Reusability**: Create reusable utility methods in BasePage
8. **Configuration**: Keep environment-specific data in config files

## Parallel Execution

To enable parallel execution, update `testng.xml`:
```xml
<suite name="Mobile Automation" parallel="tests" thread-count="2">
```

## Troubleshooting

### Appium Connection Issues
- Ensure Appium server is running on correct port
- Check `appium.server.url` in config.properties
- Verify device/emulator is running

### Driver Initialization Failures
- Verify app path is correct
- Check device name and platform version
- Ensure proper capabilities are set

### Element Not Found
- Verify element locators are correct
- Increase implicit/explicit wait times
- Check if app version has changed

## Dependencies

All dependencies are managed through Gradle. Key dependencies:

```gradle
- Appium Java Client: 9.1.0
- Selenium WebDriver: 4.15.0
- Cucumber: 7.14.0
- TestNG: 7.8.0
- SLF4J: 2.0.9
- Extent Reports: 5.1.1
```

## Contributing

1. Follow the existing code structure
2. Add meaningful logs for every action
3. Write comprehensive step definitions
4. Update documentation for new features
5. Ensure all tests pass before committing

## Support

For issues and questions:
- Review logs in `logs/automation.log`
- Check Extent Reports for detailed execution flow
- Verify configuration settings

## License

This framework is part of the skills-getting-started-with-github-copilot repository.

---

**Framework Version**: 1.0.0
**Last Updated**: January 2026
