package com.automation.pages;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;

/**
 * Sample iOS Page demonstrating iOS-specific page object
 */
public class SampleIOSPage extends BasePage {

    @iOSXCUITFindBy(id = "username")
    private WebElement usernameField;

    @iOSXCUITFindBy(id = "password")
    private WebElement passwordField;

    @iOSXCUITFindBy(id = "loginButton")
    private WebElement loginButton;

    @iOSXCUITFindBy(id = "welcomeMessage")
    private WebElement welcomeMessage;

    /**
     * Enter username
     * @param username Username to enter
     */
    public void enterUsername(String username) {
        enterText(usernameField, username, "Username Field");
    }

    /**
     * Enter password
     * @param password Password to enter
     */
    public void enterPassword(String password) {
        enterText(passwordField, password, "Password Field");
    }

    /**
     * Click login button
     */
    public void clickLoginButton() {
        click(loginButton, "Login Button");
    }

    /**
     * Get welcome message text
     * @return Welcome message
     */
    public String getWelcomeMessage() {
        return getText(welcomeMessage, "Welcome Message");
    }

    /**
     * Check if welcome message is displayed
     * @return true if displayed, false otherwise
     */
    public boolean isWelcomeMessageDisplayed() {
        return isDisplayed(welcomeMessage, "Welcome Message");
    }

    /**
     * Scroll to element (iOS implementation)
     * @param element WebElement to scroll to
     * @param elementName Name of the element for logging
     */
    @Override
    protected void scrollToElement(WebElement element, String elementName) {
        logger.info("Scrolling to element on iOS: {}", elementName);
        IOSDriver iosDriver = (IOSDriver) driver;
        // iOS-specific scroll implementation can be added here
        // Example: Using mobile: scroll for iOS
        logger.debug("Scrolled to element: {}", elementName);
    }

    /**
     * Perform login
     * @param username Username
     * @param password Password
     */
    public void performLogin(String username, String password) {
        logger.info("Performing login with username: {}", username);
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
        logger.info("Login action completed");
    }
}
