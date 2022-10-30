package org.robtest.lesson3;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class seleniumStart {
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        driver.get("https://google.com");
        Thread.sleep(5000);
        driver.quit();

        WebDriverManager.chromedriver().setup();
        WebDriver cromeDriver = new ChromeDriver();
        cromeDriver.get("https://youtube.com");
        Thread.sleep(5000);
        cromeDriver.quit();






    }
}
