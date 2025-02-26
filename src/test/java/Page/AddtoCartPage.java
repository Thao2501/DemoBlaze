package Page;

import Support.Browser;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class AddtoCartPage {
    WebDriver driver;
    WebDriverWait wait;
    public AddtoCartPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void addToCart(int productId, String productName) throws InterruptedException {
        Browser.scrollDown(driver, 500);

        // Nhấp vào sản phẩm
        driver.findElement(By.xpath("//div[@class='card-block']/h4/a[contains(@href,'prod.html?idp_=" + productId + "')]")).click();

        // Nhấn nút "Add to Cart"
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@onclick='addToCart(" + productId + ")']"))).click();

        // Xử lý alert
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        Assert.assertTrue(alert.getText().contains("Product added"));
        alert.accept();
    }
}
