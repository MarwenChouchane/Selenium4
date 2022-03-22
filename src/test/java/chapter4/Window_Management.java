package chapter4;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Iterator;
import java.util.Set;
import static org.openqa.selenium.support.locators.RelativeLocator.with;

public class Window_Management {
    WebDriver driver;

    private ChromeOptions getChromeOptions (){
        ChromeOptions options = new ChromeOptions();
        //options.addArguments("disable-infobars"); //Close the info bar (Chrome est controler par un logiciel de test automatisé)
        //options.setHeadless(true);  //Run test without opening the browser
        return options;
    }

    @BeforeMethod
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(getChromeOptions());
        driver.manage().window().maximize();
        driver.get("http://automationpractice.com/index.php");
        System.out.println("Title is : " +driver.getTitle());
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }

    @Test
    public void testNewWindowTab(){
        WebDriver newWindow = driver.switchTo().newWindow(WindowType.TAB);  //To open a new tab
    }

    @Test
    public void testNewWindowPage(){
        WebDriver newWindow = driver.switchTo().newWindow(WindowType.WINDOW);  //To open a new page in new window
        newWindow.get("http://automationpractice.com/index.php?controller=prices-drop");
        System.out.println("Title is : " +driver.getTitle());
    }

    @Test
    public void testWorkingInBothWindowsTabs(){
        //Automatically open & switch to the new window or tab
        driver.switchTo().newWindow(WindowType.TAB)
                .get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
        System.out.println("Title is : " +driver.getTitle());

        //Work in the new window or tab
        driver.findElement(By.id("email_create")).sendKeys("Selenium4@TAU.com");
        driver.findElement(By.id("SubmitCreate")).click();

        //Get the window ID handles
        Set<String> allWindowTabs = driver.getWindowHandles();
        //System.out.println(allWindowTabs);
        Iterator<String> iterator = allWindowTabs.iterator();
        String mainFirstWindow = iterator.next();
        //System.out.println(mainFirstWindow);

        //Switch and work in the main window or tab
        driver.switchTo().window(mainFirstWindow);
        driver.findElement(By.id("search_query_top")).sendKeys("Blouse");
        driver.findElement(By.name("submit_search")).click();
        System.out.println("Title is : "+driver.getTitle());
    }
}
