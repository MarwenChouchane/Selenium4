package chapter6;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class ScreenShot {
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
        //WebDriverManager.firefoxdriver().setup();
        driver = new ChromeDriver(getChromeOptions());
        //driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get("https://applitools.com/");
        System.out.println("Title is : " +driver.getTitle());
    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }

    @Test
    public void takeWebElementScreenShot() throws IOException {
        WebElement nextGenerationPlatform = driver.findElement(By.cssSelector("#post-8 h1"));
        File source = nextGenerationPlatform.getScreenshotAs(OutputType.FILE);
        File destination = new File("C:\\Users\\Administrator\\IdeaProjects\\Selenium4\\resources\\scrennshot\\Next Generation Platform.png");
        FileHandler.copy(source, destination);
    }

    @Test
    public void takeWebElementPageSectionScreenShot() throws IOException {
        WebElement appliToolPageSection = driver.findElement(By.cssSelector("#post-8>header"));
        File source = appliToolPageSection.getScreenshotAs(OutputType.FILE);
        //File destination = new File("C:\\Users\\Administrator\\IdeaProjects\\Selenium4\\resources\\scrennshot\\Appli Tool Page Section.png");
        FileUtils.copyFile(source, new File("C:\\Users\\Administrator\\IdeaProjects\\Selenium4\\resources\\scrennshot\\Appli Tool Page Section.png"));
    }

    @Test
    public void takeFullPageScreenShot() throws IOException {
        File source = ((FirefoxDriver)driver).getFullPageScreenshotAs(OutputType.FILE);  //Only with FireFox
        FileHandler.copy(source, new File("C:\\Users\\Administrator\\IdeaProjects\\Selenium4\\resources\\scrennshot\\Full Page.png"));
    }
}
