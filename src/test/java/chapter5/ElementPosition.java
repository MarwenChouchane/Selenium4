package chapter5;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.*;

public class ElementPosition {
    WebDriver driver;

    private ChromeOptions getChromeOptions (){
        ChromeOptions options = new ChromeOptions();
        //options.addArguments("disable-infobars"); //Close the info bar (Chrome est controler par un logiciel de test automatisé)
        options.setHeadless(true);  //Run test without opening the browser
        return options;
    }

    @BeforeClass
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(getChromeOptions());
        driver.manage().window().maximize();
        driver.get("https://testautomationu.applitools.com/learningpaths.html");
        System.out.println("Title is : " +driver.getTitle());
    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }

    @Test
    public void getPositionDimension(){
        WebElement logoTAU = driver.findElement(By.xpath("//div[@id='app']//header/a/img"));
        Rectangle recTAULogo = logoTAU.getRect();
        System.out.println("x = "+recTAULogo.getX() +", " +"y = "+recTAULogo.getY()
                +", "+ "Height = "+recTAULogo.getHeight() +", " +"Width = "+recTAULogo.getWidth());
    }
}
