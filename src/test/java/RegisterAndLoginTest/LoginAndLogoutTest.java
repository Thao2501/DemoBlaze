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

public class LoginAndLogoutTest {
    WebDriver driver;
    WebDriverWait wait;
    RegisterAndLoginPage registerAndLoginPage;

    @BeforeMethod
    void setUp() {
        driver = new ChromeDriver();
        driver.get("https://www.demoblaze.com/");
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        registerAndLoginPage = new RegisterAndLoginPage(driver,wait);
    }

    @Test
    void loginAndLogoutSuccess() {
        registerAndLoginPage.login("Thao250196","123456");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nameofuser")));
        Assert.assertTrue(driver.findElement(By.id("nameofuser")).getText().contains("Welcome Thao250196"));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("logout2")));
        driver.findElement(By.id("logout2")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("signin2")));
        Assert.assertTrue(driver.findElement(By.id("signin2")).getText().contains("Sign up"));
    }

    @Test
    void loginWithEmptyUsername() {
        registerAndLoginPage.login("","123456");

        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        Assert.assertTrue(alert.getText().contains("Please fill out Username and Password."));
    }

    @Test
    void loginWithEmptyPassword() {
        registerAndLoginPage.login("Thao250196","");

        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        Assert.assertTrue(alert.getText().contains("Please fill out Username and Password."));
    }

    @Test
    void loginWithWrongUsername() {
        registerAndLoginPage.login("wrongusername250196","123456");

        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        Assert.assertTrue(alert.getText().contains("User does not exist."));
    }

    @Test
    void loginWithWrongPassword() {
        registerAndLoginPage.login("Thao250196","1234567");

        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        Assert.assertTrue(alert.getText().contains("Wrong password."));
    }

    @AfterMethod
    void tearDown(){
        driver.quit();
    }
}
