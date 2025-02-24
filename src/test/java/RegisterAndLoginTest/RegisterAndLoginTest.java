package RegisterAndLoginTest;

import Page.RegisterAndLoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Random;

public class RegisterAndLoginTest {

    @Test
    void registerSuccess(){

        WebDriver driver = new ChromeDriver();
        driver.get("https://www.demoblaze.com/");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        RegisterAndLoginPage registerAndLoginPage = new RegisterAndLoginPage();

        //Open Sign-up form
        driver.findElement(By.id("signin2")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("sign-username")));

        //Input random username
        driver.findElement(By.id("sign-username")).click();
        String randomUsername = RegisterAndLoginPage.generateRandomString(8);
        driver.findElement(By.id("sign-username")).sendKeys(randomUsername);

        //Input password
        driver.findElement(By.id("sign-password")).click();
        driver.findElement(By.id("sign-password")).sendKeys("123456");

        //Click Sign-up button
        driver.findElement(By.xpath("//button[@onclick='register()']")).click();

        //Wait for alert and accept
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();

        //Click Sign-in button
        driver.findElement(By.id("login2")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loginusername")));


        //Input random username
        driver.findElement(By.id("loginusername")).click();
        driver.findElement(By.id("loginusername")).sendKeys(randomUsername);

        //Input password
        driver.findElement(By.id("loginpassword")).click();
        driver.findElement(By.id("loginpassword")).sendKeys("123456");

        //Click Sign-in button
        driver.findElement(By.xpath("//button[@onclick='logIn()']")).click();

        //Assert
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nameofuser")));
        Assert.assertTrue(driver.findElement(By.id("nameofuser")).getText().contains("Welcome " + randomUsername));
    }

}
