package org.robtest.lesson6;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
public class SuccessBlock extends BaseView{
    public SuccessBlock(WebDriver driver) {
        super(driver);
    }
    @FindBy(xpath = "//span[@class='ajax_block_cart_total']")
    private WebElement totalSumma;

    private final static String iconOkXPathLocator = "//i[@class='icon-ok']";

    @Step("Проверить сумму")
    public void checkTotalSumma(String expectedSumma) {
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(iconOkXPathLocator)));
        Assertions.assertEquals(expectedSumma, totalSumma.getText());
    }

    private static final String proceedToCheckoutButtonLocator = "//span[contains(.,'Proceed to checkout')]";
    @FindBy(xpath = proceedToCheckoutButtonLocator)
    private WebElement proceedToCheckoutButton;

    @Step("Добавить товар в корзину")
    public BasketPage addGoodToBasket() {
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(proceedToCheckoutButtonLocator)));
        actions.moveToElement(proceedToCheckoutButton)
                .click()
                .perform();
        return new BasketPage(driver);
    }
}