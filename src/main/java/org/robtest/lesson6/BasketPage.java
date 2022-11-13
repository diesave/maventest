package org.robtest.lesson6;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
public class BasketPage extends BaseView{
    public BasketPage(WebDriver driver) {
        super(driver);
    }
    private static final String deleteButtonLocator = "//i[@class='icon-trash']";
    @FindBy(xpath = deleteButtonLocator)
    private WebElement deleteButton;

    @Step("Удаление товара из корзины")
    public BasketPage deleteGoodFromBasket(){
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(deleteButtonLocator)));
        actions.moveToElement(deleteButton)
                .click()
                .perform();
        return this;
    }

    private static final String notifierLocator = "//p[.='Your shopping cart is empty.']";
    @FindBy(xpath = notifierLocator)
    private WebElement notifier;

    public void checkBasketIsEmpty() {
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(notifierLocator)));
        Assertions.assertTrue(notifier.isDisplayed());
    }
}