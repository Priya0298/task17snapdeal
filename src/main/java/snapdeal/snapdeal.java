package snapdeal;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class snapdeal {

    private WebDriver driver;
    private String url = "https://www.snapdeal.com/";

    public snapdeal() {
        System.out.println("Initializing WebDriver...");
        System.setProperty("Webdriver.gecko.driver","C:\\Users\\Dell\\Downloads\\geckodriver-v0.34.0-win64.exe");
        this.driver = new FirefoxDriver();
    }

    public void launchSnapDeal() {
        try {
            System.out.println("Launching SnapDeal...");
            driver.manage().window().maximize();
            driver.get(url);

            System.out.println("Clicking on Sign In...");
            driver.findElement(By.xpath("//span[text()='Sign In']")).click();
            driver.findElement(By.xpath("//a[text()='login']")).click();

            System.out.println("Switching to iframe...");
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement loginIframe = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("iframeLogin")));
            driver.switchTo().frame(loginIframe);

            System.out.println("Entering email...");
            WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("userName")));
            emailField.sendKeys("viya0298@gmail.com");

            System.out.println("Clicking Check User...");
            driver.findElement(By.xpath("//button[@id='checkUser']")).click();

        } catch (Exception e) {
            System.out.println("An error occurred during navigation: " + e.getMessage());
            e.printStackTrace();
        } finally {
            System.out.println("Quitting driver...");
            if (driver != null) {
                //driver.quit();
            }
            System.out.println("Driver quit, program terminated.");
        }
    }

    public static void main(String[] args) {
        System.out.println("Starting SnapdealTest1...");
        snapdeal snap = new snapdeal();
        snap.launchSnapDeal();
        System.out.println("SnapdealTest1 completed.");
    }
}