package chapter3;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static org.openqa.selenium.support.locators.RelativeLocator.with;

public class RelativeLocator {
    WebDriver driver;

    private ChromeOptions getChromeOptions (){
        ChromeOptions options = new ChromeOptions();
        //options.addArguments("disable-infobars"); //Close the info bar (Chrome est controler par un logiciel de test automatisé)
        options.setHeadless(true);  //Run test without opening the browser
        return options;
    }

    @BeforeMethod
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(getChromeOptions());
        driver.manage().window().maximize();
        driver.get("https://opensource-demo.orangehrmlive.com/");
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }

    @Test
    public void testRelativeLocator(){
        WebElement loginPanel = driver.findElement(By.id("logInPanelHeading"));
        WebElement accesCode = driver.findElement(org.openqa.selenium.support.locators.RelativeLocator
                .with(By.tagName("span")).above(loginPanel));
        System.out.println(accesCode.getText());
    }
// To find position of element "loginPanel"
// = Inspecteur -> Consol -> write :document.getElementById('logInPanelHeading').getBoundingClientRect()

    @Test
    public void testListOfElement(){
        List <WebElement> allSocialMedia = driver.findElements(with(By.tagName("img")).near(By.id("footer")));
        for (WebElement socialMedia : allSocialMedia){
            System.out.println(socialMedia.getAttribute("alt"));
        }
    }
}
