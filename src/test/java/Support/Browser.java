package Support;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class Browser {
    private static WebDriver driver;


    public static void scrollDown(WebDriver driver, int x) throws InterruptedException {
        Actions actions = new Actions(driver);

        actions.scrollByAmount(0, x).perform();
        Thread.sleep(2000);

    }
}
