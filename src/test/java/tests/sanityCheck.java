package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class sanityCheck {
    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup(); // auto-downloads matching driver
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void googleTitleShouldContainGoogle() {
        driver.get("https://www.google.com");
        String title = driver.getTitle();
        Assert.assertTrue(title.contains("Google"), "Title did not contain 'Google'");
        }

    @AfterMethod
    public void tearDown() throws Exception {
        try {
            Thread.sleep(1000);
            if (driver != null) {
                driver.quit();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
