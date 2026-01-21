package com.automation.pages;

import com.automation.drivers.DriverManager;
import com.automation.utils.ScreenshotUtils;
import com.automation.utils.WaitUtils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

/**
 * Base Page class for Page Object Model
 * All page objects should extend this class
 */
public abstract class BasePage {
    protected static final Logger logger = LoggerFactory.getLogger(BasePage.class);
    protected AppiumDriver driver;

    /**
     * Constructor to initialize page elements
     */
    public BasePage() {
        this.driver = DriverManager.getDriver();
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)), this);
        logger.debug("Initialized page: {}", this.getClass().getSimpleName());
    }

    /**
     * Click on element with logging
     * @param element WebElement to click
     * @param elementName Name of the element for logging
     */
    protected void click(WebElement element, String elementName) {
        logger.info("Clicking on element: {}", elementName);
        element.click();
        logger.debug("Clicked on element: {}", elementName);
    }

    /**
     * Click on element using locator with explicit wait
     * @param locator By locator
     * @param elementName Name of the element for logging
     */
    protected void click(By locator, String elementName) {
        logger.info("Clicking on element: {}", elementName);
        WebElement element = WaitUtils.waitForElementClickable(locator);
        element.click();
        logger.debug("Clicked on element: {}", elementName);
    }

    /**
     * Enter text into element with logging
     * @param element WebElement to enter text
     * @param text Text to enter
     * @param elementName Name of the element for logging
     */
    protected void enterText(WebElement element, String text, String elementName) {
        logger.info("Entering text '{}' into element: {}", text, elementName);
        element.clear();
        element.sendKeys(text);
        logger.debug("Text entered into element: {}", elementName);
    }

    /**
     * Enter text using locator with explicit wait
     * @param locator By locator
     * @param text Text to enter
     * @param elementName Name of the element for logging
     */
    protected void enterText(By locator, String text, String elementName) {
        logger.info("Entering text '{}' into element: {}", text, elementName);
        WebElement element = WaitUtils.waitForElementVisible(locator);
        element.clear();
        element.sendKeys(text);
        logger.debug("Text entered into element: {}", elementName);
    }

    /**
     * Get text from element with logging
     * @param element WebElement to get text from
     * @param elementName Name of the element for logging
     * @return Element text
     */
    protected String getText(WebElement element, String elementName) {
        logger.debug("Getting text from element: {}", elementName);
        String text = element.getText();
        logger.info("Text from element '{}': {}", elementName, text);
        return text;
    }

    /**
     * Get text using locator with explicit wait
     * @param locator By locator
     * @param elementName Name of the element for logging
     * @return Element text
     */
    protected String getText(By locator, String elementName) {
        logger.debug("Getting text from element: {}", elementName);
        WebElement element = WaitUtils.waitForElementVisible(locator);
        String text = element.getText();
        logger.info("Text from element '{}': {}", elementName, text);
        return text;
    }

    /**
     * Check if element is displayed with logging
     * @param element WebElement to check
     * @param elementName Name of the element for logging
     * @return true if displayed, false otherwise
     */
    protected boolean isDisplayed(WebElement element, String elementName) {
        logger.debug("Checking if element is displayed: {}", elementName);
        boolean isDisplayed = element.isDisplayed();
        logger.info("Element '{}' displayed: {}", elementName, isDisplayed);
        return isDisplayed;
    }

    /**
     * Check if element is displayed using locator
     * @param locator By locator
     * @param elementName Name of the element for logging
     * @return true if displayed, false otherwise
     */
    protected boolean isDisplayed(By locator, String elementName) {
        logger.debug("Checking if element is displayed: {}", elementName);
        try {
            WebElement element = WaitUtils.waitForElementVisible(locator, 5);
            boolean isDisplayed = element.isDisplayed();
            logger.info("Element '{}' displayed: {}", elementName, isDisplayed);
            return isDisplayed;
        } catch (Exception e) {
            logger.info("Element '{}' not displayed", elementName);
            return false;
        }
    }

    /**
     * Wait for element to be visible
     * @param locator By locator
     * @param elementName Name of the element for logging
     * @return WebElement
     */
    protected WebElement waitForElement(By locator, String elementName) {
        logger.debug("Waiting for element: {}", elementName);
        return WaitUtils.waitForElementVisible(locator);
    }

    /**
     * Scroll to element (to be implemented based on platform)
     * @param element WebElement to scroll to
     * @param elementName Name of the element for logging
     */
    protected abstract void scrollToElement(WebElement element, String elementName);

    /**
     * Take screenshot of current page
     * @param screenshotName Screenshot name
     * @return Screenshot file path
     */
    protected String takeScreenshot(String screenshotName) {
        logger.info("Taking screenshot: {}", screenshotName);
        return ScreenshotUtils.captureScreenshot(screenshotName, true);
    }
}
