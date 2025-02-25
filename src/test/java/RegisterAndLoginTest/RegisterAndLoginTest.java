package RegisterAndLoginTest;

import Page.RegisterAndLoginPage;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class RegisterAndLoginTest {
    WebDriver driver = new ChromeDriver();
    RegisterAndLoginPage registerAndLoginPage;
    @BeforeMethod
    void setUp() {

        driver.get("https://www.demoblaze.com/");
    }

    @Test
    void registerSuccess() {


        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        registerAndLoginPage = new RegisterAndLoginPage();

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

        //Wait for alert
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        Assert.assertEquals("Sign up successful.", alertText);

        //Accept alert
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

    @Test
    void registerWithExistUsername() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        registerAndLoginPage = new RegisterAndLoginPage();

        //Open Sign-up form
        driver.findElement(By.id("signin2")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("sign-username")));

        //Input random username
        driver.findElement(By.id("sign-username")).click();
        driver.findElement(By.id("sign-username")).sendKeys("Thao250196");

        //Input password
        driver.findElement(By.id("sign-password")).click();
        driver.findElement(By.id("sign-password")).sendKeys("123456");

        //Click Sign-up button
        driver.findElement(By.xpath("//button[@onclick='register()']")).click();

        //Wait for alert and accept
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        Assert.assertEquals("This user already exist.", alertText);
        driver.switchTo().alert().accept();


    }
    @Test
    void registerWithEmptyUsername() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        registerAndLoginPage = new RegisterAndLoginPage();

        //Open Sign-up form
        driver.findElement(By.id("signin2")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("sign-username")));

        //Input password
        driver.findElement(By.id("sign-password")).click();
        driver.findElement(By.id("sign-password")).sendKeys("123456");

        //Click Sign-up button
        driver.findElement(By.xpath("//button[@onclick='register()']")).click();

        //Wait for alert and accept
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        Assert.assertEquals("Please fill out Username and Password.", alertText);
        driver.switchTo().alert().accept();


    }
    @Test
    void registerWithEmptyPassword() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        registerAndLoginPage = new RegisterAndLoginPage();

        //Open Sign-up form
        driver.findElement(By.id("signin2")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("sign-username")));

        //Input random username
        driver.findElement(By.id("sign-username")).click();
        driver.findElement(By.id("sign-username")).sendKeys("Thao250196");

        //Click Sign-up button
        driver.findElement(By.xpath("//button[@onclick='register()']")).click();

        //Wait for alert and accept
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        Assert.assertEquals("Please fill out Username and Password.", alertText);
        driver.switchTo().alert().accept();
    }
    @Test
    void registerWithEmptyUserNameAndPassword() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        registerAndLoginPage = new RegisterAndLoginPage();

        //Open Sign-up form
        driver.findElement(By.id("signin2")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("sign-username")));

        //Click Sign-up button
        driver.findElement(By.xpath("//button[@onclick='register()']")).click();

        //Wait for alert and accept
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        Assert.assertEquals("Please fill out Username and Password.", alertText);
        driver.switchTo().alert().accept();

    }
    @AfterMethod
    void tearDown() {
        driver.quit();
    }
}
