package org.robtest.lesson6;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
public class MainPage extends BaseView {
    public MainPage(WebDriver driver) {
        super(driver);
    }
    @FindBy(xpath = "//a[@class='login']")
    private WebElement signInButton;

    @Step("Клик на кнопку Войти")
    public LoginPage clickSignInButton() {
        signInButton.click();
        return new LoginPage(driver);
    }
}