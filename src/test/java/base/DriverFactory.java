package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import utils.ConfigReader;

import java.util.HashMap;
import java.util.Map;

public class DriverFactory {
    // ThreadLocal keeps each test thread's driver separate (needed for parallel runs)
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static void initDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized", "--disable-notifications");

        if (ConfigReader.getBoolean("headless")) {
            options.addArguments("--headless=new", "--window-size=1280,900");
        }

        if ("mobile".equalsIgnoreCase(ConfigReader.get("viewport"))) {
            Map<String, Object> mobileEmulation = new HashMap<>();
            mobileEmulation.put("deviceName", "iPhone 12 Pro");
            options.setExperimentalOption("mobileEmulation", mobileEmulation);
        }

        driver.set(new ChromeDriver(options));
    }

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}