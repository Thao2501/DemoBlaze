package AddToCartTest;

import Page.AddtoCartPage;
import Support.Browser;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='cart.html']")));
        driver.findElement(By.xpath("//a[@href='cart.html']")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tbody[@id='tbodyid']//td[contains(text(),'" + productName + "')]")));
        Assert.assertTrue(driver.findElement(By.xpath("//tbody[@id='tbodyid']//td[contains(text(),'" + productName + "')]")).getText().contains("Samsung galaxy s6"));
    }
    @Test
    void addFirstProductToCart(){
        driver.get("https://www.demoblaze.com/");
        driver.manage().window().maximize();

        // Chọn danh mục "Phones"
        driver.findElement(By.linkText("Phones")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("card-title")));

        // Chọn sản phẩm đầu tiên trong danh sách
        WebElement firstProduct = driver.findElements(By.className("card-title")).get(0);
        String productName = firstProduct.getText();  // Lấy tên sản phẩm
        firstProduct.click();

        // Chờ nút "Add to cart" xuất hiện và click
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Add to cart']"))).click();

        // Chờ alert hiển thị thông báo và chấp nhận
        wait.until(ExpectedConditions.alertIsPresent()).accept();
        System.out.println("✅ Đã thêm sản phẩm vào giỏ hàng: " + productName);

        // Chuyển đến trang giỏ hàng
        driver.findElement(By.id("cartur")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("success")));

        // Kiểm tra sản phẩm đã có trong giỏ hàng
        WebElement cartItem = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[contains(text(),'" + productName + "')]")));
        if (cartItem.isDisplayed()) {
            System.out.println("✅ Sản phẩm hiển thị trong giỏ hàng: " + productName);
        } else {
            System.out.println("❌ Lỗi: Sản phẩm không có trong giỏ hàng.");
        }
    }

    @AfterMethod
    void tearDown() {
        driver.quit();
    }
}
