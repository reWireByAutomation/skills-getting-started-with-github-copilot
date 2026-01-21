package com.automation.utils;

import com.automation.config.ConfigManager;
import com.automation.drivers.DriverManager;
import io.appium.java_client.AppiumDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Screenshot utility for capturing screenshots during test execution
 */
public class ScreenshotUtils {
    private static final Logger logger = LoggerFactory.getLogger(ScreenshotUtils.class);
    private static final String SCREENSHOT_DIR = ConfigManager.getProperty("screenshot.path", "screenshots/");

    /**
     * Take screenshot and save to file
     * @param scenarioName Name of the scenario
     * @return Screenshot file path
     */
    public static String captureScreenshot(String scenarioName) {
        AppiumDriver driver = DriverManager.getDriver();
        
        if (driver == null) {
            logger.warn("Driver is null, cannot capture screenshot");
            return null;
        }

        try {
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String fileName = scenarioName.replaceAll("[^a-zA-Z0-9]", "_") + "_" + timestamp + ".png";
            String filePath = SCREENSHOT_DIR + fileName;

            File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File destinationFile = new File(filePath);
            
            // Create directory if it doesn't exist
            destinationFile.getParentFile().mkdirs();
            
            FileUtils.copyFile(screenshotFile, destinationFile);
            logger.info("Screenshot captured: {}", filePath);
            
            return filePath;
        } catch (IOException e) {
            logger.error("Failed to capture screenshot for scenario: {}", scenarioName, e);
            return null;
        }
    }

    /**
     * Take screenshot with custom file name
     * @param fileName Custom file name
     * @return Screenshot file path
     */
    public static String captureScreenshot(String fileName, boolean includeTimestamp) {
        AppiumDriver driver = DriverManager.getDriver();
        
        if (driver == null) {
            logger.warn("Driver is null, cannot capture screenshot");
            return null;
        }

        try {
            String finalFileName = fileName.replaceAll("[^a-zA-Z0-9]", "_");
            if (includeTimestamp) {
                String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                finalFileName += "_" + timestamp;
            }
            finalFileName += ".png";
            
            String filePath = SCREENSHOT_DIR + finalFileName;

            File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File destinationFile = new File(filePath);
            
            // Create directory if it doesn't exist
            destinationFile.getParentFile().mkdirs();
            
            FileUtils.copyFile(screenshotFile, destinationFile);
            logger.info("Screenshot captured: {}", filePath);
            
            return filePath;
        } catch (IOException e) {
            logger.error("Failed to capture screenshot: {}", fileName, e);
            return null;
        }
    }
}
