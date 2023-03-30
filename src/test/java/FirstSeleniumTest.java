import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.*;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class FirstSeleniumTest {
    WebDriver webDriver;

    @BeforeTest
    public void beforeTest(){
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.manage().timeouts().pageLoadTimeout(Duration.ofDays(5));
    }

    @Test
    public void firstTest(){
        webDriver.get("https://stackoverflow.com/");
        WebDriverWait wait = new WebDriverWait(webDriver, 180);
        WebElement inputField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"search\"]/div/input")));

        //WebElement inputField = webDriver.findElement(By.tagName("input1"));
        inputField.sendKeys("human verification");

        inputField.sendKeys(Keys.ENTER);



        String header = webDriver.getTitle();
        Assert.assertEquals(header, "Join the Stack Overflow community");

        List <WebElement> listElements = webDriver.findElements(By.xpath("//h3"));
        Assert.assertTrue(listElements.size()==10);
    }

    @AfterTest
    public void afterTest() {
        webDriver.quit();
    }
}
