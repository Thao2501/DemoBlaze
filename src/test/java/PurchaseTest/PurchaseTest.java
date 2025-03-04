package PurchaseTest;

import Page.AddtoCartPage;
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

public class PurchaseTest {
    WebDriver driver;
    WebDriverWait wait;
    AddtoCartPage addtoCartPage;

    @BeforeMethod
    void setUp() {
        driver = new ChromeDriver();
        driver.get("https://www.demoblaze.com/index.html");
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        addtoCartPage = new AddtoCartPage(driver, wait);
    }

    @Test
    void purchaseSuccess() throws InterruptedException {

        int productId = 1;  // ID của sản phẩm Samsung Galaxy S6
        String productName = "Samsung galaxy s6";

        addtoCartPage.addToCart(productId, productName);

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='cart.html']")));
        driver.findElement(By.xpath("//a[@href='cart.html']")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tbody[@id='tbodyid']//td[contains(text(),'" + productName + "')]")));
        Assert.assertTrue(driver.findElement(By.xpath("//tbody[@id='tbodyid']//td[contains(text(),'" + productName + "')]")).getText().contains("Samsung galaxy s6"));

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@data-target='#orderModal']")));
        driver.findElement(By.xpath("//button[@data-target='#orderModal']")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("name")));

        driver.findElement(By.id("name")).sendKeys("This is name");
        driver.findElement(By.id("country")).sendKeys("This is country");
        driver.findElement(By.id("city")).sendKeys("This is city");
        driver.findElement(By.id("card")).sendKeys("This is card");
        driver.findElement(By.id("month")).sendKeys("This is month");
        driver.findElement(By.id("year")).sendKeys("This is year");

        driver.findElement(By.xpath("//button[@onclick='purchaseOrder()']")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("name")));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("showSweetAlert")));
        Assert.assertTrue(driver.findElement(By.xpath("//h2[contains(text(),'Thank you for your purchase!')]")).getText().contains("Thank you for your purchase!"));

    }

    @AfterMethod
    void tearDown() {
        driver.quit();
    }
}
