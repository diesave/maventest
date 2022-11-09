package lesson5;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;

public class ActionsTest {
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
    }

    @Test
    void highlighTextTest() throws InterruptedException {
        driver.get("https://translate.google.com/?hl=ru&sl=en&tl=ru&text=test&op=translate");
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//textarea[@aria-label='Исходный текст']/following-sibling::div/span")));
        actions.moveToElement(driver.findElement(By.xpath("//textarea[@aria-label='Исходный текст']/following-sibling::div/span")), -20, 0)
                .clickAndHold()
                .moveByOffset(30, 0)
                .perform();

        Thread.sleep(5000);

    }

    @Test
    void yetNewExamples() throws InterruptedException {
        driver.get("https://google.com");
        ((JavascriptExecutor) driver).executeScript("alert('asdf');");
        Thread.sleep(1000);
        driver.switchTo().alert().accept();
        Thread.sleep(5000);

        driver.switchTo().newWindow(WindowType.TAB);
        Thread.sleep(1000);

        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));

        driver.get("https://ya.ru");
        Thread.sleep(2000);

        driver.switchTo().window(tabs.get(0));
        driver.close();
        Thread.sleep(2000);
    }

    @Test
    void afishaAuthTest() {
        driver.findElement(By.xpath("//button[.='Войти']")).click();
        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@src), 'login']")));

        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("login")));

        driver.findElement(By.id("login")).sendKeys("kakoytologin");
        driver.findElement(By.id("password")).sendKeys("kakoytopasswodr");

        webDriverWait.until(d -> d.findElement(By.id("login")).getAttribute("value").contains("@rambler.ru"));
        driver.findElement(By.xpath("//button[.='Войти']"));

        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("//header//a//div[contains(.,'Избранное') and preceding-sibling::span]")));
        Assertions.assertTrue(driver.findElement(By.xpath("//header//a//div[contains(.,'Избранное') and preceding-sibling::span]")).isDisplayed());
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }
}