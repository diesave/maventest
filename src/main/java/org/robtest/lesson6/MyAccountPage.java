package org.robtest.lesson6;
import org.openqa.selenium.WebDriver;
public class MyAccountPage extends BaseView {
    public NavigationBlock navigationBlock;
    public MyAccountPage(WebDriver driver) {
        super(driver);
        navigationBlock = new NavigationBlock(driver);
    }


}