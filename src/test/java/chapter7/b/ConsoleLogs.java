package chapter7.b;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v85.log.Log;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class ConsoleLogs {
    //ChromeDriver driver;
    EdgeDriver driver;

    private ChromeOptions getChromeOptions (){
        ChromeOptions options = new ChromeOptions();
        //options.addArguments("disable-infobars"); //Close the info bar (Chrome est controler par un logiciel de test automatisé)
        options.setHeadless(true);  //Run test without opening the browser
        return options;
    }

    @BeforeClass
    public void setUp(){
        //WebDriverManager.chromedriver().setup();
        WebDriverManager.edgedriver().setup();
        //driver = new ChromeDriver(getChromeOptions());
        driver = new EdgeDriver();
        driver.manage().window().maximize();
    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }

    @Test
    public void viewsBrowsersConsoleLogs(){
        //Get the devTools & create a session
        DevTools devTools = driver.getDevTools();
        devTools.createSession();  //Take control of the developer tool

        //Enable console Logs
        devTools.send(Log.enable());  //send() = to interact with devTools

        //Add a listener for the console logs
        devTools.addListener(Log.entryAdded(), logEntry -> {
            System.out.println("----------");
            System.out.println("Level : "+logEntry.getLevel());
            System.out.println("Text : "+logEntry.getText());
            System.out.println("Broken URL : "+logEntry.getUrl());
        });

        //Load the AUT
        driver.get("https://the-internet.herokuapp.com/broken_images");
    }
}
