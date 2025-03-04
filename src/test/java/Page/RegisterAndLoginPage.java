package Page;

import Support.Browser;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class RegisterAndLoginPage {
    WebDriver driver;
    WebDriverWait wait;

    public RegisterAndLoginPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    private By confirmLoginButton = By.xpath("//button[@onclick='logIn()']");
    private By usernameLogin = By.id("loginusername");
    private By passwordLogin = By.id("loginpassword");

    private By confirmRegisterButton = By.xpath("//button[@onclick='register()']");
    private By usernameRegister = By.id("sign-username");
    private By passwordRegister = By.id("sign-password");

    private By loginButton = By.id("login2");
    private By registerButton = By.id("signin2");


    public void login(String username, String password) {

        Browser.click(loginButton);

        wait.until(ExpectedConditions.visibilityOfElementLocated(usernameLogin));

        driver.findElement(usernameLogin).sendKeys(username);

        driver.findElement(passwordLogin).sendKeys(password);

        Browser.click(confirmLoginButton);
    }

    public void register(String username, String password, String alertText) {

        //Open Sign-up form
        Browser.click(registerButton);

        wait.until(ExpectedConditions.visibilityOfElementLocated(usernameRegister));
        //Input username
        driver.findElement(usernameRegister).sendKeys(username);

        //Input password
        driver.findElement(passwordRegister).sendKeys(password);

        //Click Sign-up button
        Browser.click(confirmRegisterButton);

        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        String text = alert.getText();
        Assert.assertEquals(alertText, text);
        driver.switchTo().alert().accept();
    }
}
