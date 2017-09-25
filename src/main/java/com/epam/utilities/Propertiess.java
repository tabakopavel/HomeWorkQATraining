package com.epam.utilities;

import org.testng.Assert;
import org.testng.Reporter;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;


public class Propertiess {

    private static FileInputStream fis;
    private static Properties properties = new Properties();

    public static void init() {
        try {
            fis = new FileInputStream(".\\src\\main\\resources\\config.properties");
            properties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }

        Enumeration<String> enumeration = (Enumeration<String>) properties.propertyNames();
        while (enumeration.hasMoreElements()) {
            String key = enumeration.nextElement();
            System.setProperty(key, properties.getProperty(key));
        }
    }

}
