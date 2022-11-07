package org.robtest.lesson3;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.Random;

public class DiaryExample {
    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();

        WebDriver driver = new ChromeDriver();
        driver.get("https://diary.ru/user/login");

//        driver.findElement(By.id("loginform-username")).sendKeys("spartalex");
//        driver.findElement(By.id("loginform-password")).sendKeys("123456");
//        Thread.sleep(5000);
//        driver.findElement(By.name("login-button")).click();

        Cookie authCookie = new Cookie("_identity","83668234c30812b14c46bac1deda1a240770255504032650b" +
                "424a75038c00b3aa%3A2%3A%7Bi%3A0%3Bs%3A10%3A%22_identity_%22%3Bi%3A1%3Bs%3A52%3A%22%5B3545507%2C%2" +
                "2E_QJqRaNdBrepyeVN7uNXi5Dz9tjGpfX%22%2C2592000%5D%22%3B%7D");
        driver.manage().addCookie(authCookie);

        driver.navigate().refresh();

       driver.findElement(By.id("writeThisDiary")).click();

       String postTitle = "titile" + new Random().nextInt(100);
        driver.findElement(By.name("BlogsPosts[title]")).sendKeys(postTitle);

        driver.switchTo().frame(driver.findElement(By.id("message_ifr")));
        driver.findElement(By.id("tinymce")).sendKeys("test text");

        driver.switchTo().parentFrame();
        driver.findElement(By.id("rewrite")).click();

       List <WebElement> titles = (List<WebElement>) driver.findElement(By.xpath("//a[@class='title']"));
        titles.stream().filter(p -> p.getText().equals(postTitle)).findFirst().get().click();







        Thread.sleep(5000);
        driver.quit();
    }
}
