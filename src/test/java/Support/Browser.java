package Support;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.security.SecureRandom;
import java.time.Duration;
import java.util.Random;

public class Browser {
    private static WebDriver driver;
    private static WebDriverWait wait;

    public static void init(WebDriver driverInstance) {
        driver = driverInstance;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public static void openBrowser(String browser) {
        switch (browser) {
            case "chrome": {
                driver = new ChromeDriver();
                break;
            }
            case "firefox": {
                driver = new FirefoxDriver();
                break;
            }
            case "Safari": {
                driver = new SafariDriver();
                break;
            }
        }
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    public static void scrollDown(WebDriver driver, int x) throws InterruptedException {
        Actions actions = new Actions(driver);

        actions.scrollByAmount(0, x).perform();
        Thread.sleep(2000);

    }

    private static final String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
    private static final String DIGITS = "0123456789";
    private static final String SPECIALS = "!@#$%^&*()-_=+<>?";
    private static final String ALL_CHARS = UPPERCASE + LOWERCASE + DIGITS + SPECIALS;
    private static final int PASSWORD_LENGTH = 8; // Độ dài tối thiểu

    public static String randomPassword() {
        Random random = new SecureRandom();
        StringBuilder password = new StringBuilder();

        // Đảm bảo mật khẩu có đủ các loại ký tự cần thiết
        password.append(UPPERCASE.charAt(random.nextInt(UPPERCASE.length())));
        password.append(LOWERCASE.charAt(random.nextInt(LOWERCASE.length())));
        password.append(DIGITS.charAt(random.nextInt(DIGITS.length())));
        password.append(SPECIALS.charAt(random.nextInt(SPECIALS.length())));

        // Thêm các ký tự ngẫu nhiên để đạt độ dài mong muốn
        for (int i = 4; i < PASSWORD_LENGTH; i++) {
            password.append(ALL_CHARS.charAt(random.nextInt(ALL_CHARS.length())));
        }
        return password.toString();

    }

    public static void click(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    public static void fill(By locator, CharSequence... withText) {

        driver.findElement(locator).sendKeys(withText);
    }

}
