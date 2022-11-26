package org.robtest.lesson6;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.List;

public class TShirtPage extends BaseView {
    public TShirtPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//span[.='Size']/ancestor::div[@class='layered_filter']//a")
    private List<WebElement> sizesList;
    @Step("Выбрать размер")
    public TShirtPage selectSize(String size) {
        webDriverWait.until(d -> sizesList.size() > 0);
        sizesList.stream().filter(s -> s.getText().contains(size)).findFirst().get().click();
        return this;
    }
    @FindBy(xpath = "//div[contains(@class,'slider')]/a[1]")
    private WebElement leftPriceSliderElement;

    @Step("Изменить фильтр стоимости")
    public TShirtPage moveLeftPriceSliderElement(int pixelsCount) {
        actions.clickAndHold(leftPriceSliderElement)
                .moveByOffset(pixelsCount, 0)
                .perform();
        return this;
    }
    @FindBy(xpath = "//div[@class='product-container']")
    private List<WebElement> dressesList;
    private static final String addToCartButtonXPathLocator = "//span[.='Add to cart']";

    @FindBy(xpath = addToCartButtonXPathLocator)
    private WebElement addToCartButton;

    @Step("Добавить в карточку по названию")
    public SuccessBlock addToCardByName(String tshirtName) {
        actions.moveToElement(driver.findElement(By.xpath("//a[@class=" +
                        "'product-name' and @title='Faded Short Sleeve T-shirts']")))
                .perform();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath(addToCartButtonXPathLocator)));
        dressesList.stream().filter(d -> d.getText().contains(tshirtName)).findFirst()
                .get().findElement(By.xpath(addToCartButtonXPathLocator)).click();
        return new SuccessBlock(driver);
    }
}