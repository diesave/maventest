package org.robtest.lesson6;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Allure;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.support.events.EventFiringDecorator;

import org.robtest.lesson7.AdditionalLogger;
import org.robtest.lesson7.JUnitExtention;

import java.io.ByteArrayInputStream;
@Story("Работа с корзиной")
public class BuyTShirtTest {
    WebDriver driver;
    MainPage mainPage;
    @RegisterExtension
    TestWatcher testWatcher = new JUnitExtention();

    @BeforeAll
    static void registerDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void initDriver() {
        driver = new EventFiringDecorator(new AdditionalLogger()).decorate(new ChromeDriver());
        mainPage = new MainPage(driver);
        driver.get("http://automationpractice.com/index.php");
    }
    @Test
    @Feature("Добавление в корзину")
    public void bueTShirtTest(){
        mainPage.clickSignInButton()
                .login("evch@rambler.ru", "test4test")
                .navigationBlock.hoverWomenMenuAndClickTShirts()
                .selectSize("S")
                .moveLeftPriceSliderElement(5)
                .addToCardByName("Faded")
                .checkTotalSumma("$18.51");
    }
    @Test
    @Feature("Удаление из корзины")
    public void deleteGoodFromBasket () {
        mainPage.clickSignInButton()
                .login("evch@rambler.ru", "test4test")
                .navigationBlock.hoverWomenMenuAndClickTShirts()
                .selectSize("S")
                .moveLeftPriceSliderElement(5)
                .addToCardByName("Faded 1")
                .addGoodToBasket()
                .deleteGoodFromBasket()
                .checkBasketIsEmpty();
    }
    @AfterEach
    void tearDown() {
        LogEntries logs = driver.manage().logs().get(LogType.BROWSER);
        for (LogEntry log : logs) {
            Allure.addAttachment("Browser stacktrace:\n", log.getMessage());
        }
        ((JUnitExtention) testWatcher)
                .setScreenShot(new ByteArrayInputStream(((TakesScreenshot) driver)
                        .getScreenshotAs(OutputType.BYTES)));
        driver.quit();
    }
}