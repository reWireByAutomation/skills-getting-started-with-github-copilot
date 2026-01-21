package com.automation.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Configuration Manager to load and manage properties from config files
 */
public class ConfigManager {
    private static final Logger logger = LoggerFactory.getLogger(ConfigManager.class);
    private static Properties properties;
    private static final String CONFIG_FILE_PATH = "src/test/resources/config/config.properties";

    static {
        loadProperties();
    }

    /**
     * Load properties from config file
     */
    private static void loadProperties() {
        properties = new Properties();
        try (FileInputStream fis = new FileInputStream(CONFIG_FILE_PATH)) {
            properties.load(fis);
            logger.info("Configuration properties loaded successfully from: {}", CONFIG_FILE_PATH);
        } catch (IOException e) {
            logger.error("Failed to load configuration properties from: {}", CONFIG_FILE_PATH, e);
            throw new RuntimeException("Failed to load configuration properties", e);
        }
    }

    /**
     * Get property value by key
     * @param key Property key
     * @return Property value
     */
    public static String getProperty(String key) {
        String value = properties.getProperty(key);
        if (value == null) {
            logger.warn("Property '{}' not found in config file", key);
        }
        return value;
    }

    /**
     * Get property value with default fallback
     * @param key Property key
     * @param defaultValue Default value if key not found
     * @return Property value or default
     */
    public static String getProperty(String key, String defaultValue) {
        String value = properties.getProperty(key, defaultValue);
        logger.debug("Retrieved property '{}' = '{}'", key, value);
        return value;
    }

    /**
     * Get platform type (android/ios)
     * @return Platform type
     */
    public static String getPlatformType() {
        return getProperty("platform.type");
    }

    /**
     * Get Appium server URL
     * @return Appium server URL
     */
    public static String getAppiumServerUrl() {
        return getProperty("appium.server.url");
    }

    /**
     * Check if platform is Android
     * @return true if Android, false otherwise
     */
    public static boolean isAndroid() {
        return "android".equalsIgnoreCase(getPlatformType());
    }

    /**
     * Check if platform is iOS
     * @return true if iOS, false otherwise
     */
    public static boolean isIOS() {
        return "ios".equalsIgnoreCase(getPlatformType());
    }
}
