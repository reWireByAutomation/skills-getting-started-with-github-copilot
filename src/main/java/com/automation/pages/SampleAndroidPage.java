package com.automation.pages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

/**
 * Sample Android Page demonstrating Android-specific page object
 */
public class SampleAndroidPage extends BasePage {

    @AndroidFindBy(id = "com.example.app:id/username")
    private WebElement usernameField;

    @AndroidFindBy(id = "com.example.app:id/password")
    private WebElement passwordField;

    @AndroidFindBy(id = "com.example.app:id/login_button")
    private WebElement loginButton;

    @AndroidFindBy(id = "com.example.app:id/welcome_message")
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
     * Scroll to element (Android implementation)
     * @param element WebElement to scroll to
     * @param elementName Name of the element for logging
     */
    @Override
    protected void scrollToElement(WebElement element, String elementName) {
        logger.info("Scrolling to element on Android: {}", elementName);
        AndroidDriver androidDriver = (AndroidDriver) driver;
        // Android-specific scroll implementation can be added here
        // Example: Using UiScrollable for Android
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
