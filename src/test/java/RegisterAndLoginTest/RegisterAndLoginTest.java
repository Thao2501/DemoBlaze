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
    WebDriver driver;
    RegisterAndLoginPage registerAndLoginPage;
    WebDriverWait wait;

    @BeforeMethod
    void setUp() {
        driver = new ChromeDriver();
        driver.get("https://www.demoblaze.com/");
        wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        registerAndLoginPage = new RegisterAndLoginPage(driver,wait);
    }

    @Test
    void registerSuccess() {

        String randomUsername = RegisterAndLoginPage.generateRandomString(8);
        registerAndLoginPage.register(randomUsername,"123456");

        //Wait for alert
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        Assert.assertEquals("Sign up successful.", alertText);

        //Accept alert
        driver.switchTo().alert().accept();

        registerAndLoginPage.login(randomUsername,"123456");

        //Assert
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nameofuser")));
        Assert.assertTrue(driver.findElement(By.id("nameofuser")).getText().contains("Welcome " + randomUsername));
    }

    @Test
    void registerWithExistUsername() {

        registerAndLoginPage.register("Thao250196","123456");

        //Wait for alert and accept
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        Assert.assertEquals("This user already exist.", alertText);
        driver.switchTo().alert().accept();

    }
    @Test
    void registerWithEmptyUsername() {

        registerAndLoginPage.register("","123456");

        //Wait for alert and accept
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        Assert.assertEquals("Please fill out Username and Password.", alertText);
        driver.switchTo().alert().accept();


    }
    @Test
    void registerWithEmptyPassword() {
        registerAndLoginPage.register("Thao250196","");

        //Wait for alert and accept
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        Assert.assertEquals("Please fill out Username and Password.", alertText);
        driver.switchTo().alert().accept();
    }
    @Test
    void registerWithEmptyUserNameAndPassword() {

        registerAndLoginPage.register("","");

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
