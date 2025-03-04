package RegisterAndLoginTest;

import Page.RegisterAndLoginPage;
import Support.Browser;
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
        Browser.init(driver);

    }

    @Test
    void registerSuccess() {

        String randomUsername = "testuser" + System.currentTimeMillis();
        String randomPassword = Browser.randomPassword();

        registerAndLoginPage.register(randomUsername,randomPassword,"Sign up successful.");

        registerAndLoginPage.login(randomUsername, randomPassword);

        //Assert
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nameofuser")));
        Assert.assertTrue(driver.findElement(By.id("nameofuser")).getText().contains("Welcome " + randomUsername));
    }

    @Test
    void registerWithExistUsername() {

        registerAndLoginPage.register("Thao250196","123456","This user already exist.");


    }
    @Test
    void registerWithEmptyUsername() {

        registerAndLoginPage.register("","123456","Please fill out Username and Password.");

    }
    @Test
    void registerWithEmptyPassword() {
        registerAndLoginPage.register("Thao250196","","Please fill out Username and Password.");

    }
    @Test
    void registerWithEmptyUserNameAndPassword() {

        registerAndLoginPage.register("","","Please fill out Username and Password.");

    }
    @AfterMethod
    void tearDown() {
        driver.quit();
    }
}
