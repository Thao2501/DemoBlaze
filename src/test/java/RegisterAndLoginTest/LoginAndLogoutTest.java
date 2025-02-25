package RegisterAndLoginTest;

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

public class LoginAndLogoutTest {
    WebDriver driver;

    @BeforeMethod
    void setUp() {
        driver = new ChromeDriver();
        driver.get("https://www.demoblaze.com/");
    }

    @Test
    void loginAndLogoutSuccess() {
        driver.findElement(By.id("login2")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loginusername")));

        driver.findElement(By.id("loginusername")).click();
        driver.findElement(By.id("loginusername")).sendKeys("Thao250196");

        driver.findElement(By.id("loginpassword")).click();
        driver.findElement(By.id("loginpassword")).sendKeys("123456");

        driver.findElement(By.xpath("//button[@onclick='logIn()']")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nameofuser")));
        Assert.assertTrue(driver.findElement(By.id("nameofuser")).getText().contains("Welcome Thao250196"));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("logout2")));
        driver.findElement(By.id("logout2")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("signin2")));
        Assert.assertTrue(driver.findElement(By.id("signin2")).getText().contains("Sign up"));


    }

    @Test
    void loginWithEmptyUsername() {
        driver.findElement(By.id("login2")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loginusername")));

        driver.findElement(By.id("loginusername")).click();
        driver.findElement(By.id("loginusername")).sendKeys("");

        driver.findElement(By.id("loginpassword")).click();
        driver.findElement(By.id("loginpassword")).sendKeys("123456");

        driver.findElement(By.xpath("//button[@onclick='logIn()']")).click();

        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        Assert.assertTrue(alert.getText().contains("Please fill out Username and Password."));

    }

    @Test
    void loginWithEmptyPassword() {
        driver.findElement(By.id("login2")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loginusername")));

        driver.findElement(By.id("loginusername")).click();
        driver.findElement(By.id("loginusername")).sendKeys("Thao250196");

        driver.findElement(By.id("loginpassword")).click();
        driver.findElement(By.id("loginpassword")).sendKeys("");

        driver.findElement(By.xpath("//button[@onclick='logIn()']")).click();

        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        Assert.assertTrue(alert.getText().contains("Please fill out Username and Password."));
    }

    @Test
    void loginWithWrongUsername() {
        driver.findElement(By.id("login2")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loginusername")));

        driver.findElement(By.id("loginusername")).click();
        driver.findElement(By.id("loginusername")).sendKeys("wrongusername250196");

        driver.findElement(By.id("loginpassword")).click();
        driver.findElement(By.id("loginpassword")).sendKeys("123456");

        driver.findElement(By.xpath("//button[@onclick='logIn()']")).click();

        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        Assert.assertTrue(alert.getText().contains("User does not exist."));
    }

    @Test
    void loginWithWrongPassword() {
        driver.findElement(By.id("login2")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loginusername")));

        driver.findElement(By.id("loginusername")).click();
        driver.findElement(By.id("loginusername")).sendKeys("Thao250196");

        driver.findElement(By.id("loginpassword")).click();
        driver.findElement(By.id("loginpassword")).sendKeys("1234567");

        driver.findElement(By.xpath("//button[@onclick='logIn()']")).click();

        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        Assert.assertTrue(alert.getText().contains("Wrong password."));
    }

    @AfterMethod
    void tearDown(){
        driver.quit();
    }
}
