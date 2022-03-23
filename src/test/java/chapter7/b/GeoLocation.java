package chapter7.b;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v97.emulation.Emulation;
import java.util.Optional;
import org.testng.annotations.*;
import java.util.HashMap;
import java.util.Map;

public class GeoLocation {
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
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void tearDown(){
        //driver.quit();
    }

    @Test
    public void mockGeoLocation_ExecuteCdpCommand (){
        Map coordinates = new HashMap()
        {{
            //put("latitude ", 32.746940);
            put("latitude ", 52.5043);
            //put("longitude", -97.092400);
            put("longitude", 13.4501);
            put("accuracy", 1);

        }};
        ((ChromeDriver)driver).executeCdpCommand("Emulation.setGeolocationOverride", coordinates);
        //driver.get("https://where-am-i.org/");
        driver.get("https://my-location.org/");
    }

    @Test
    public void mockGeoLocation_DevTools (){
        DevTools devTools = ((ChromeDriver)driver).getDevTools();
        devTools.createSession();
        devTools.send(Emulation.setGeolocationOverride(Optional.of(52.5043),
                                                       Optional.of(13.4501),
                                                       Optional.of(1)));
        driver.get("https://my-location.org/");
    }
}
