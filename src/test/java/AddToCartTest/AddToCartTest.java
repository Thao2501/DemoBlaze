package AddToCartTest;

import Page.AddtoCartPage;
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

public class AddToCartTest {
    WebDriver driver;
    WebDriverWait wait;
    AddtoCartPage addtoCartPage;

    @BeforeMethod
    void setUp() {
        driver = new ChromeDriver();
        driver.get("https://www.demoblaze.com/index.html");
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        addtoCartPage = new AddtoCartPage(driver,wait);
    }

    @Test
    void addToCartSuccess() throws InterruptedException {

        int productId = 1;  // ID của sản phẩm Samsung Galaxy S6
        String productName = "Samsung galaxy s6";

        addtoCartPage.addToCart(productId, productName);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='cart.html']")));
        driver.findElement(By.xpath("//a[@href='cart.html']")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tbody[@id='tbodyid']//td[contains(text(),'" + productName + "')]")));
        Assert.assertTrue(driver.findElement(By.xpath("//tbody[@id='tbodyid']//td[contains(text(),'" + productName + "')]")).getText().contains("Samsung galaxy s6"));
    }

    @AfterMethod
    void tearDown() {
        driver.quit();
    }
}
