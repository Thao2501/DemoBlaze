package Support;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.security.SecureRandom;
import java.util.Random;

public class Browser {
    private static WebDriver driver;


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
}
