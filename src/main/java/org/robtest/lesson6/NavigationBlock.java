package org.robtest.lesson6;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
public class NavigationBlock extends BaseView {
    public NavigationBlock(WebDriver driver) {
        super(driver);
    }
    @FindBy(xpath = "//li/a[.='Women']")
    private WebElement womenButton;
    @FindBy(xpath = "//ul[contains(@class, 'submenu')]//a[.='T-shirts']")
    private WebElement tshirtsButtonInSubmenu;
    @Step("Навести курсор мыши на раздел Wemen и кликнуть на TShirts")
    public TShirtPage hoverWomenMenuAndClickTShirts() {
        webDriverWait.until(ExpectedConditions.visibilityOf(womenButton));
        actions.moveToElement(womenButton)
                .perform();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(tshirtsButtonInSubmenu));
        tshirtsButtonInSubmenu.click();
        return new TShirtPage(driver);
    }
}