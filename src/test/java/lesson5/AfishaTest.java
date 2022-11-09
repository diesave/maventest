package lesson5;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AfishaTest {
    WebDriver driver;
    WebDriverWait webDriverWait;
    Actions actions;

    @BeforeAll
    static void registerDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setupBrowser() {
        driver = new ChromeDriver();
        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(5));
        actions = new Actions(driver);
        driver.get("https://afisha.ru");
    }

    @Test
    void goToOkkoTest() throws InterruptedException {

        Thread.sleep(12000);
        ((JavascriptExecutor) driver).executeScript(
                "let element = document.evaluate(\"//*[contains(text(),'Ваш город')]/../..\", document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null);\n" +
                        "element.singleNodeValue.remove();"
        );

        ((JavascriptExecutor) driver).executeScript(
                "let element = document.evaluate(\"//div[@data-test='HONEY-AD AD-ad_1']\", document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null);\n" +
                        "element.singleNodeValue.remove();"
        );


        ((JavascriptExecutor) driver).executeScript(
                "let element = document.evaluate(\"//button[@id='fucking-privacy']/../../../..\", document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null);\n" +
                        "element.singleNodeValue.remove();"
        );

        ((JavascriptExecutor) driver).executeScript(
                "let element = document.evaluate(\"//*[@id='popmechanic-container-40776']\", document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null);\n" +
                        "element.singleNodeValue.remove();"
        );

        actions.moveToElement(driver.findElement(By.xpath("//a[.='КИНО']")))
                .perform();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//li//a[.='Скоро онлайн в Okko']")).click();
        Assertions.assertTrue(driver.getCurrentUrl().contains("okko"));

        Thread.sleep(1000);
        //button[.='Подписаться']
        actions.scrollToElement(driver.findElement(By.xpath("//button[.='Подписаться']"))).perform();

        Thread.sleep(5000);

    }
    @AfterEach
    void tearDown() {
        driver.quit();
    }
}

