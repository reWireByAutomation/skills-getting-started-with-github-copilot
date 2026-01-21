# Mobile Apps Automation Framework

> A production-ready, reusable, and scalable framework for automated testing of Android and iOS mobile applications using Appium, Cucumber BDD, and TestNG.

## 🚀 Quick Links

- **[Quick Start Guide](QUICK_START.md)** - Get up and running in 5 minutes
- **[Complete Documentation](MOBILE_AUTOMATION_FRAMEWORK.md)** - Comprehensive framework documentation

## ✨ Key Features

| Feature | Description |
|---------|-------------|
| 📱 **Cross-Platform** | Single framework for both Android and iOS apps |
| 🥒 **BDD with Cucumber** | Write tests in natural Gherkin language |
| 🏗️ **Page Object Model** | Maintainable and reusable page objects |
| 📊 **Rich Reporting** | Extent Reports + Cucumber HTML reports |
| 📝 **SLF4J Logging** | Granular logging for every event |
| 📸 **Auto Screenshots** | Capture screenshots on test failures |
| ⚙️ **Configurable** | Easy platform switching via properties |
| ✅ **TestNG Integration** | Powerful test execution and assertions |

## 📋 Tech Stack

```
Language:        Java 11
Mobile Testing:  Appium 9.1.0
BDD Framework:   Cucumber 7.14.0
Build Tool:      Gradle
Test Framework:  TestNG 7.8.0
Logging:         SLF4J 2.0.9 + Logback
Reporting:       Extent Reports 5.1.1
```

## 🏃 Quick Start

```bash
# 1. Install Appium
npm install -g appium
appium driver install uiautomator2

# 2. Configure your app
# Edit src/test/resources/config/config.properties
platform.type=android
android.app.path=/path/to/your/app.apk

# 3. Start Appium server
appium

# 4. Build and run tests
gradle clean build
gradle test
```

## 📁 Project Structure

```
mobile-automation-framework/
├── src/
│   ├── main/java/com/automation/
│   │   ├── config/       # Configuration management
│   │   ├── drivers/      # Driver initialization
│   │   ├── pages/        # Page objects
│   │   └── utils/        # Utilities (Wait, Screenshot)
│   └── test/
│       ├── java/com/automation/
│       │   ├── runners/         # TestNG runners
│       │   └── stepdefinitions/ # Cucumber steps
│       └── resources/
│           ├── features/        # Gherkin feature files
│           └── config/          # Configuration files
├── build.gradle          # Dependencies and build config
├── QUICK_START.md       # Quick start guide
└── MOBILE_AUTOMATION_FRAMEWORK.md  # Full documentation
```

## 🎯 Sample Test

```gherkin
Feature: User Login
  @smoke
  Scenario: Successful login
    Given I am on the login screen
    When I enter username "test@example.com"
    And I enter password "Test@123"
    And I click on login button
    Then I should see the welcome message
```

## 📊 Reports

After running tests, view reports:
- **Extent Report**: `extent-reports/Spark.html`
- **Cucumber Report**: `cucumber-reports/cucumber.html`
- **Logs**: `logs/automation.log`

## 🔧 Requirements Met

✅ **Language**: Java  
✅ **Mobile Framework**: Appium  
✅ **BDD**: Cucumber with Gherkin  
✅ **Build Tool**: Gradle  
✅ **Assertions**: TestNG  
✅ **Reporting**: Cucumber + Extent Reports  
✅ **Platform Support**: Android & iOS  
✅ **Logging**: SLF4J with granular event tracking  

## 📖 Documentation

- [Quick Start Guide](QUICK_START.md) - Get started quickly
- [Framework Documentation](MOBILE_AUTOMATION_FRAMEWORK.md) - Complete guide
  - Setup instructions
  - Configuration guide
  - Writing tests
  - Best practices
  - Troubleshooting

## 🛠️ Framework Components

### Core Classes
- **DriverManager**: Manages Appium driver lifecycle for Android/iOS
- **ConfigManager**: Centralized configuration management
- **BasePage**: Abstract base class for page objects
- **WaitUtils**: Explicit wait utilities
- **ScreenshotUtils**: Screenshot capture functionality

### Test Infrastructure
- **TestRunner**: TestNG-Cucumber integration
- **Hooks**: Setup/teardown with logging
- **Sample Tests**: Example scenarios and page objects

## 🤝 Contributing

Follow the framework patterns:
1. Use Page Object Model for all page interactions
2. Add SLF4J logging for every action
3. Write BDD scenarios in Gherkin
4. Update documentation for new features

## 📄 License

This project is part of the skills-getting-started-with-github-copilot repository.

---

**Happy Testing! 🚀**
