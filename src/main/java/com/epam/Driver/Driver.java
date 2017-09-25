package com.epam.Driver;

import com.epam.utilities.Propertiess;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.SECONDS;

public class Driver {

    private static WebDriver driver;
    private static WebDriver input_driver;
    private static String pageLoadStatus = null;
    private static JavascriptExecutor js;
    private static FirefoxProfile profile = new FirefoxProfile();

    /**
     * Returns Driver
     */
    public static WebDriver getDriver() {
        return driver;
    }

    private static void setDriver(WebDriver input_driver) {
        driver = input_driver;
    }

    /**
     * SetPageLoadTimeOut
     */
    public static void waitfor(long time) {
        Driver.getDriver().manage().timeouts().pageLoadTimeout(5, SECONDS);
    }

    /**
     * Init Driver with Parameters,
     * specified in properties.
     */
    public static void init() {
        Propertiess.init();
        if (System.getProperty("test.browser").equalsIgnoreCase("Firefox")) {
            profile.setPreference("browser.startup.homepage", "about:blank");
            input_driver = new FirefoxDriver(profile);
        } else if (System.getProperty("test.browser").equalsIgnoreCase("Chrome")) {
            input_driver = new ChromeDriver();
        } else if (System.getProperty("test.browser").equalsIgnoreCase("Opera")) {
            input_driver = new OperaDriver();
        }
        //input_driver.manage().timeouts().implicitlyWait(Integer.parseInt(System.getProperty("test.timeout")), TimeUnit.SECONDS);
        Driver.setDriver(input_driver);
    }

    public static void waitForAjax() {
        try {
            WebDriverWait driverWait = new WebDriverWait(Driver.getDriver(), Integer.parseInt(System.getProperty("test.jstimeout")));
            ExpectedCondition<Boolean> expectation;
            expectation = new ExpectedCondition<Boolean>() {
                public Boolean apply(WebDriver driverjs) {
                    JavascriptExecutor js = (JavascriptExecutor) driverjs;
                    return js.executeScript("return((window.jQuery != null) && (jQuery.active == 0))").equals("true");
                }
            };
            driverWait.until(expectation);
        } catch (TimeoutException exTimeout) {

            exTimeout.printStackTrace();
        } catch (WebDriverException exWebDriverException) {

            exWebDriverException.printStackTrace();
        }

    }

    public static void waitForPageToLoad() {
        do {
            js = (JavascriptExecutor) Driver.getDriver();
            pageLoadStatus = (String) js.executeScript("return document.readyState");

        } while (!pageLoadStatus.equals("complete"));
    }

    public static boolean waitForJSandJQueryToLoad() {

        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), 30);

        // wait for jQuery to load
        ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                try {
                    return ((Long) ((JavascriptExecutor) getDriver()).executeScript("return jQuery.active") == 0);
                } catch (Exception e) {
                    // no jQuery present
                    return true;
                }
            }
        };
        // wait for Javascript to load
        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) getDriver()).executeScript("return document.readyState")
                        .toString().equals("complete");
            }
        };

        return wait.until(jQueryLoad) && wait.until(jsLoad);
    }

    public static ExpectedCondition<Boolean> jQueryAJAXCallsHaveCompleted() {
        return new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return (Boolean) ((JavascriptExecutor) driver).executeScript("return (window.jQuery != null) && (jQuery.active == 0);");
            }
        };
    }

    public static boolean isElementPresent(By locator) {
        try {
            WebElement qwe = new WebDriverWait(Driver.getDriver(), Integer.parseInt(System.getProperty("test.wait_timeout"))).until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (org.openqa.selenium.TimeoutException e) {
            return false;
        }
        return true;
    }

    public static void clean() {
        Driver.getDriver().quit();
    }
}
