package Page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.Random;

public class RegisterAndLoginPage {
    WebDriver driver;
    WebDriverWait wait;
    public RegisterAndLoginPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }
    public static String generateRandomString(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            sb.append(characters.charAt(random.nextInt(characters.length())));
        }

        return sb.toString();
    }

    public void login(String username, String password){

        driver.findElement(By.id("login2")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loginusername")));

        driver.findElement(By.id("loginusername")).sendKeys(username);

        driver.findElement(By.id("loginpassword")).sendKeys(password);

        driver.findElement(By.xpath("//button[@onclick='logIn()']")).click();

    }

    public void register(String username, String password){

        //Open Sign-up form
        driver.findElement(By.id("signin2")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("sign-username")));

        //Input username
        driver.findElement(By.id("sign-username")).sendKeys(username);

        //Input password
        driver.findElement(By.id("sign-password")).sendKeys(password);

        //Click Sign-up button
        driver.findElement(By.xpath("//button[@onclick='register()']")).click();

    }
}
