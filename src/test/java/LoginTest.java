import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class LoginTest {

    WebDriver driver ;

    SoftAssert soft = new SoftAssert() ;

    @BeforeTest
    public void openBrowser()
    {
        // Open Browser
        driver =  new FirefoxDriver();
        // Maximize Window
        driver.manage().window().maximize();
    }

    @Test
    public void ValidLogin()
    {
        // Navigate to Website
        driver.navigate().to("https://the-internet.herokuapp.com/login");
        // Enter Username
        driver.findElement(By.id("username")).sendKeys("tomsmith");
        // Enter Password
        driver.findElement(By.name("password")).sendKeys("SuperSecretPassword!");
        // Click on Login
        driver.findElement(By.cssSelector("button[type=\"submit\"]")).click();
        // Check Success Message is displayed or not
        soft.assertTrue(driver.findElement(By.xpath("//div[@id=\"flash\"]")).isDisplayed() , "Assert 1 ");
        // Check success message Text
        soft.assertTrue(driver.findElement(By.id("flash")).getText().contains("You logged into a secure area!") , "Assert 2 ");

        soft.assertAll();
    }

    @Test
    public void invalidUsernameLogin()
    {
        // Navigate to Website
        driver.navigate().to("https://the-internet.herokuapp.com/login");
        // Enter Username
        driver.findElement(By.id("username")).sendKeys("invalid");
        // Enter Password
        driver.findElement(By.name("password")).sendKeys("SuperSecretPassword!");
        // Click on Login
        driver.findElement(By.cssSelector("button[type=\"submit\"]")).click();
        // Check Error Message
        Assert.assertTrue(driver.findElement(By.id("flash")).isDisplayed());
        // Check Error Message Text
        Assert.assertTrue(driver.findElement(By.id("flash")).getText().contains("Your username is invalid!"));
    }

    @Test
    public void invalidPasswordLogin()
    {
        // Navigate to Website
        driver.navigate().to("https://the-internet.herokuapp.com/login");
        // Enter Username
        driver.findElement(By.id("username")).sendKeys("tomsmith");
        // Enter Password
        driver.findElement(By.name("password")).sendKeys("invalid!");
        // Click on Login
        driver.findElement(By.cssSelector("button[type=\"submit\"]")).click();
        // Check Error Message
        Assert.assertTrue(driver.findElement(By.id("flash")).isDisplayed());
        // Check Error Message Text
        Assert.assertTrue(driver.findElement(By.id("flash")).getText().contains("Your password is invalid!"));
    }

    @AfterTest
    public void closeBrowser()
    {
        driver.quit();
    }
}
