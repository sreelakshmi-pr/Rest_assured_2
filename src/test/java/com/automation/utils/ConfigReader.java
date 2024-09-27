package com.automation.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    static Properties prop;

    public static void initConfig() {
        prop = new Properties();
        try {
            prop.load(new FileInputStream("src/test/resources/config/config.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getConfigValue(String key) {
        return prop.getProperty(key);
    }

    public static void setConfigValue(String key, String value) {
        prop.setProperty(key, value);
        try {
            prop.save(new FileOutputStream("src/test/resources/config/config.properties"),"sucess"+value);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
