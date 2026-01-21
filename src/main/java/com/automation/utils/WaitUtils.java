package com.automation.utils;

import com.automation.config.ConfigManager;
import com.automation.drivers.DriverManager;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

/**
 * Wait utility for explicit waits and element interactions
 */
public class WaitUtils {
    private static final Logger logger = LoggerFactory.getLogger(WaitUtils.class);
    private static final int DEFAULT_TIMEOUT = Integer.parseInt(
            ConfigManager.getProperty("explicit.wait", "20"));

    /**
     * Wait for element to be visible
     * @param locator Element locator
     * @return WebElement
     */
    public static WebElement waitForElementVisible(By locator) {
        return waitForElementVisible(locator, DEFAULT_TIMEOUT);
    }

    /**
     * Wait for element to be visible with custom timeout
     * @param locator Element locator
     * @param timeoutInSeconds Timeout in seconds
     * @return WebElement
     */
    public static WebElement waitForElementVisible(By locator, int timeoutInSeconds) {
        logger.debug("Waiting for element to be visible: {}", locator);
        AppiumDriver driver = DriverManager.getDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            logger.debug("Element is visible: {}", locator);
            return element;
        } catch (Exception e) {
            logger.error("Element not visible within {} seconds: {}", timeoutInSeconds, locator, e);
            throw e;
        }
    }

    /**
     * Wait for element to be clickable
     * @param locator Element locator
     * @return WebElement
     */
    public static WebElement waitForElementClickable(By locator) {
        return waitForElementClickable(locator, DEFAULT_TIMEOUT);
    }

    /**
     * Wait for element to be clickable with custom timeout
     * @param locator Element locator
     * @param timeoutInSeconds Timeout in seconds
     * @return WebElement
     */
    public static WebElement waitForElementClickable(By locator, int timeoutInSeconds) {
        logger.debug("Waiting for element to be clickable: {}", locator);
        AppiumDriver driver = DriverManager.getDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        
        try {
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
            logger.debug("Element is clickable: {}", locator);
            return element;
        } catch (Exception e) {
            logger.error("Element not clickable within {} seconds: {}", timeoutInSeconds, locator, e);
            throw e;
        }
    }

    /**
     * Wait for element to be present
     * @param locator Element locator
     * @return WebElement
     */
    public static WebElement waitForElementPresent(By locator) {
        return waitForElementPresent(locator, DEFAULT_TIMEOUT);
    }

    /**
     * Wait for element to be present with custom timeout
     * @param locator Element locator
     * @param timeoutInSeconds Timeout in seconds
     * @return WebElement
     */
    public static WebElement waitForElementPresent(By locator, int timeoutInSeconds) {
        logger.debug("Waiting for element to be present: {}", locator);
        AppiumDriver driver = DriverManager.getDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        
        try {
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            logger.debug("Element is present: {}", locator);
            return element;
        } catch (Exception e) {
            logger.error("Element not present within {} seconds: {}", timeoutInSeconds, locator, e);
            throw e;
        }
    }

    /**
     * Wait for element to be invisible
     * @param locator Element locator
     */
    public static void waitForElementInvisible(By locator) {
        waitForElementInvisible(locator, DEFAULT_TIMEOUT);
    }

    /**
     * Wait for element to be invisible with custom timeout
     * @param locator Element locator
     * @param timeoutInSeconds Timeout in seconds
     */
    public static void waitForElementInvisible(By locator, int timeoutInSeconds) {
        logger.debug("Waiting for element to be invisible: {}", locator);
        AppiumDriver driver = DriverManager.getDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
            logger.debug("Element is invisible: {}", locator);
        } catch (Exception e) {
            logger.error("Element still visible after {} seconds: {}", timeoutInSeconds, locator, e);
            throw e;
        }
    }

    /**
     * Custom wait with specified duration
     * @param seconds Number of seconds to wait
     */
    public static void customWait(int seconds) {
        logger.debug("Custom wait for {} seconds", seconds);
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            logger.error("Wait interrupted", e);
            Thread.currentThread().interrupt();
        }
    }
}
