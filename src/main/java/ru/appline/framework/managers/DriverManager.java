package ru.appline.framework.managers;

import org.apache.commons.exec.OS;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;


import java.net.MalformedURLException;
import java.net.URI;
import java.util.Map;

import static ru.appline.framework.utils.PropConst.*;

public class DriverManager {

    private static WebDriver driver;
    private static TestPropManager propManager = TestPropManager.getTestPropManager();
    private static DriverManager INSTANCE = null;

    private DriverManager() {

    }

    public static WebDriver getDriver() {
        if (driver == null) {
            initRemoteDriver();
        }
        return driver;
    }

    public static DriverManager getDriverManager() {
        if (INSTANCE == null) {
            INSTANCE = new DriverManager();
        }
        return INSTANCE;
    }

    private static void initRemoteDriver() {
        String browser = System.getProperty("browser", "chrome");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS,true);
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", false);
        capabilities.setBrowserName(browser);
        switch (browser) {
            case "chrome":
            case "firefox":
                capabilities.setVersion("109.0");
                break;
            case "opera":
                capabilities.setVersion("94.0");
        }
        try {
            driver = new RemoteWebDriver(URI.create("http://149.154.71.152:4444/wd/hub").toURL(), capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }


}
