package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.time.Duration;

public class DriverFactory {
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        if (driver.get() == null) {
            // Use the cached ChromeDriver matching version 149.0.7827.54
            //System.setProperty("webdriver.chrome.driver", "C:\\Users\\Jeki\\.cache\\selenium\\chromedriver\\win64\\149.0.7827.54\\chromedriver.exe");
            
            ChromeOptions options = new ChromeOptions();
            options.setBinary("C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe");
            
            // Configure headless mode by default (false means it will display the browser visually)
            String headlessProp = System.getProperty("headless", "false");
            if (Boolean.parseBoolean(headlessProp)) {
                options.addArguments("--headless=new");
            }
            options.addArguments("--disable-gpu");
            options.addArguments("--window-size=1920,1080");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--remote-allow-origins=*");

            WebDriver webDriver = new ChromeDriver(options);
            webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
            webDriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
            webDriver.manage().window().setSize(new org.openqa.selenium.Dimension(1920, 1080));
            
            driver.set(webDriver);
        }
        return driver.get();
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            try {
                driver.get().quit();
            } catch (Exception e) {
                System.err.println("Error quitting driver: " + e.getMessage());
            } finally {
                driver.remove();
            }
        }
    }
}
