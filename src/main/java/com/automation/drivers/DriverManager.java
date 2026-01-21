package com.automation.drivers;

import com.automation.config.ConfigManager;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

/**
 * Driver Manager to manage Appium driver lifecycle for Android and iOS
 */
public class DriverManager {
    private static final Logger logger = LoggerFactory.getLogger(DriverManager.class);
    private static final ThreadLocal<AppiumDriver> driver = new ThreadLocal<>();

    /**
     * Get the current driver instance
     * @return AppiumDriver instance
     */
    public static AppiumDriver getDriver() {
        return driver.get();
    }

    /**
     * Initialize driver based on platform configuration
     */
    public static void initializeDriver() {
        logger.info("Initializing driver for platform: {}", ConfigManager.getPlatformType());
        
        try {
            AppiumDriver appiumDriver;
            if (ConfigManager.isAndroid()) {
                appiumDriver = createAndroidDriver();
            } else if (ConfigManager.isIOS()) {
                appiumDriver = createIOSDriver();
            } else {
                throw new IllegalArgumentException("Invalid platform type: " + ConfigManager.getPlatformType());
            }
            
            driver.set(appiumDriver);
            configureTimeouts(appiumDriver);
            logger.info("Driver initialized successfully for platform: {}", ConfigManager.getPlatformType());
        } catch (Exception e) {
            logger.error("Failed to initialize driver", e);
            throw new RuntimeException("Failed to initialize driver", e);
        }
    }

    /**
     * Create Android driver with capabilities
     * @return AndroidDriver instance
     */
    private static AndroidDriver createAndroidDriver() throws MalformedURLException {
        logger.info("Creating Android driver with capabilities");
        
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", ConfigManager.getProperty("android.device.name"));
        capabilities.setCapability("platformVersion", ConfigManager.getProperty("android.platform.version"));
        capabilities.setCapability("automationName", ConfigManager.getProperty("android.automation.name"));
        capabilities.setCapability("app", ConfigManager.getProperty("android.app.path"));
        capabilities.setCapability("appPackage", ConfigManager.getProperty("android.app.package"));
        capabilities.setCapability("appActivity", ConfigManager.getProperty("android.app.activity"));
        capabilities.setCapability("autoGrantPermissions", Boolean.parseBoolean(ConfigManager.getProperty("auto.grant.permissions", "true")));
        capabilities.setCapability("noReset", Boolean.parseBoolean(ConfigManager.getProperty("no.reset", "false")));
        capabilities.setCapability("fullReset", Boolean.parseBoolean(ConfigManager.getProperty("full.reset", "false")));
        
        logger.debug("Android capabilities: {}", capabilities);
        
        return new AndroidDriver(new URL(ConfigManager.getAppiumServerUrl()), capabilities);
    }

    /**
     * Create iOS driver with capabilities
     * @return IOSDriver instance
     */
    private static IOSDriver createIOSDriver() throws MalformedURLException {
        logger.info("Creating iOS driver with capabilities");
        
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "iOS");
        capabilities.setCapability("deviceName", ConfigManager.getProperty("ios.device.name"));
        capabilities.setCapability("platformVersion", ConfigManager.getProperty("ios.platform.version"));
        capabilities.setCapability("automationName", ConfigManager.getProperty("ios.automation.name"));
        capabilities.setCapability("app", ConfigManager.getProperty("ios.app.path"));
        capabilities.setCapability("bundleId", ConfigManager.getProperty("ios.bundle.id"));
        capabilities.setCapability("noReset", Boolean.parseBoolean(ConfigManager.getProperty("no.reset", "false")));
        capabilities.setCapability("fullReset", Boolean.parseBoolean(ConfigManager.getProperty("full.reset", "false")));
        
        logger.debug("iOS capabilities: {}", capabilities);
        
        return new IOSDriver(new URL(ConfigManager.getAppiumServerUrl()), capabilities);
    }

    /**
     * Configure timeouts for the driver
     * @param appiumDriver Driver instance
     */
    private static void configureTimeouts(AppiumDriver appiumDriver) {
        int implicitWait = Integer.parseInt(ConfigManager.getProperty("implicit.wait", "10"));
        appiumDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWait));
        logger.debug("Configured implicit wait: {} seconds", implicitWait);
    }

    /**
     * Quit and cleanup driver
     */
    public static void quitDriver() {
        if (driver.get() != null) {
            logger.info("Quitting driver for platform: {}", ConfigManager.getPlatformType());
            driver.get().quit();
            driver.remove();
            logger.info("Driver quit successfully");
        }
    }
}
