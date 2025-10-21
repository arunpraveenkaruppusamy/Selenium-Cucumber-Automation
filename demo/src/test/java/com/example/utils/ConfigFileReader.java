package com.example.utils;
import java.io.*;
import java.util.Properties;

public class ConfigFileReader {
    private Properties properties;
    // We don't need the propertyFilePath field anymore!

    public ConfigFileReader() {
        // 💡 CHANGE 1: Read from the classpath using getResourceAsStream
        try (InputStream reader = this.getClass().getClassLoader().getResourceAsStream("config.properties")) {
            if (reader == null) {
                // If it's null, the file wasn't found in the resources folder
                throw new FileNotFoundException("config.properties not found in classpath (src/test/resources or src/main/resources).");
            }
            properties = new Properties();
            properties.load(reader);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Config.properties not found: Make sure it's directly under src/test/resources or src/main/resources.", e);
        } catch (IOException e) {
            throw new RuntimeException("Error reading config.properties file.", e);
        }
    }

    public String getBrowser() {
        String browserName = properties.getProperty("browser");
        if (browserName != null) return browserName;
        throw new RuntimeException("Browser property not specified in the config.properties file.");
    }
}