package com.automation.stepdefinitions;

import com.automation.config.ConfigManager;
import com.automation.drivers.DriverManager;
import com.automation.utils.ScreenshotUtils;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Cucumber Hooks for setup and teardown operations
 */
public class Hooks {
    private static final Logger logger = LoggerFactory.getLogger(Hooks.class);

    /**
     * Setup before each scenario
     * @param scenario Cucumber scenario
     */
    @Before
    public void beforeScenario(Scenario scenario) {
        logger.info("========================================");
        logger.info("Starting Scenario: {}", scenario.getName());
        logger.info("Platform: {}", ConfigManager.getPlatformType());
        logger.info("========================================");
        
        try {
            DriverManager.initializeDriver();
            logger.info("Driver initialized successfully for scenario: {}", scenario.getName());
        } catch (Exception e) {
            logger.error("Failed to initialize driver for scenario: {}", scenario.getName(), e);
            throw new RuntimeException("Driver initialization failed", e);
        }
    }

    /**
     * Teardown after each scenario
     * @param scenario Cucumber scenario
     */
    @After
    public void afterScenario(Scenario scenario) {
        logger.info("========================================");
        logger.info("Completing Scenario: {}", scenario.getName());
        logger.info("Status: {}", scenario.getStatus());
        logger.info("========================================");

        // Capture screenshot on failure
        if (scenario.isFailed()) {
            logger.warn("Scenario failed, capturing screenshot");
            String screenshotPath = ScreenshotUtils.captureScreenshot(scenario.getName());
            if (screenshotPath != null) {
                logger.info("Screenshot saved at: {}", screenshotPath);
            }
        }

        // Quit driver
        try {
            DriverManager.quitDriver();
            logger.info("Driver quit successfully for scenario: {}", scenario.getName());
        } catch (Exception e) {
            logger.error("Failed to quit driver for scenario: {}", scenario.getName(), e);
        }
    }
}
