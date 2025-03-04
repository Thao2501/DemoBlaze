package CategoryNavigationTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class CategoryNavigationTest {
    @Test
    void CategoryNavigationTest() throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get("https://www.demoblaze.com/index.html");
        driver.manage().window().maximize();

        List<String> categories = new ArrayList<>();
        categories.add("Phones");
        categories.add("Laptops");
        categories.add("Monitors");

        for (String category : categories) {
            driver.findElement(By.linkText(category)).click();
            wait.until(ExpectedConditions.presenceOfElementLocated(By.className("card-title")));

            List<WebElement> products = driver.findElements(By.className("card-title"));
            if (products.size() > 0) {
                System.out.println("Danh muc " + category + "co " + products.size() + " san pham ");
            } else {
                System.out.println("Danh muc " + category + "khong co san pham");
            }
        }
    }
}
