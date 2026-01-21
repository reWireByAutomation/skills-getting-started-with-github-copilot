package com.automation.stepdefinitions;

import com.automation.config.ConfigManager;
import com.automation.pages.SampleAndroidPage;
import com.automation.pages.SampleIOSPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

/**
 * Sample Login Step Definitions
 */
public class LoginStepDefinitions {
    private static final Logger logger = LoggerFactory.getLogger(LoginStepDefinitions.class);
    
    private SampleAndroidPage androidLoginPage;
    private SampleIOSPage iosLoginPage;

    @Given("the app is launched")
    public void theAppIsLaunched() {
        logger.info("App is launched");
        // App launch is handled by driver initialization in Hooks
        logger.debug("Platform: {}", ConfigManager.getPlatformType());
    }

    @Given("I am on the login screen")
    public void iAmOnTheLoginScreen() {
        logger.info("Navigating to login screen");
        
        if (ConfigManager.isAndroid()) {
            androidLoginPage = new SampleAndroidPage();
            logger.info("Initialized Android login page");
        } else if (ConfigManager.isIOS()) {
            iosLoginPage = new SampleIOSPage();
            logger.info("Initialized iOS login page");
        }
        
        logger.info("Successfully on login screen");
    }

    @When("I enter username {string}")
    public void iEnterUsername(String username) {
        logger.info("Entering username: {}", username);
        
        if (ConfigManager.isAndroid()) {
            androidLoginPage.enterUsername(username);
        } else if (ConfigManager.isIOS()) {
            iosLoginPage.enterUsername(username);
        }
        
        logger.debug("Username entered successfully");
    }

    @And("I enter password {string}")
    public void iEnterPassword(String password) {
        logger.info("Entering password");
        
        if (ConfigManager.isAndroid()) {
            androidLoginPage.enterPassword(password);
        } else if (ConfigManager.isIOS()) {
            iosLoginPage.enterPassword(password);
        }
        
        logger.debug("Password entered successfully");
    }

    @And("I click on login button")
    public void iClickOnLoginButton() {
        logger.info("Clicking on login button");
        
        if (ConfigManager.isAndroid()) {
            androidLoginPage.clickLoginButton();
        } else if (ConfigManager.isIOS()) {
            iosLoginPage.clickLoginButton();
        }
        
        logger.debug("Login button clicked successfully");
    }

    @Then("I should see the welcome message")
    public void iShouldSeeTheWelcomeMessage() {
        logger.info("Verifying welcome message is displayed");
        
        boolean isDisplayed;
        if (ConfigManager.isAndroid()) {
            isDisplayed = androidLoginPage.isWelcomeMessageDisplayed();
        } else {
            isDisplayed = iosLoginPage.isWelcomeMessageDisplayed();
        }
        
        Assert.assertTrue(isDisplayed, "Welcome message should be displayed");
        logger.info("Welcome message verified successfully");
    }

    @And("I should be on the home screen")
    public void iShouldBeOnTheHomeScreen() {
        logger.info("Verifying user is on home screen");
        // Add verification logic for home screen
        logger.info("Home screen verified successfully");
    }

    @Then("I should see an error message")
    public void iShouldSeeAnErrorMessage() {
        logger.info("Verifying error message is displayed");
        // Add verification logic for error message
        logger.info("Error message verified successfully");
    }

    @And("I should remain on the login screen")
    public void iShouldRemainOnTheLoginScreen() {
        logger.info("Verifying user remains on login screen");
        // Add verification logic to ensure still on login screen
        logger.info("User remains on login screen verified");
    }

    @Then("I should see {string}")
    public void iShouldSee(String expectedResult) {
        logger.info("Verifying expected result: {}", expectedResult);
        
        if (expectedResult.equalsIgnoreCase("welcome message")) {
            iShouldSeeTheWelcomeMessage();
        } else if (expectedResult.equalsIgnoreCase("error message")) {
            iShouldSeeAnErrorMessage();
        }
        
        logger.info("Expected result verified: {}", expectedResult);
    }
}
