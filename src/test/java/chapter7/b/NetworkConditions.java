package chapter7.b;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v85.network.model.ConnectionType;
import org.openqa.selenium.devtools.v97.network.Network;
import org.testng.annotations.*;

import java.util.Optional;

public class NetworkConditions {
    ChromeDriver driver;
    DevTools devTools;

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
        devTools = driver.getDevTools();
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }

    @Test
    public void enableSlowRexJonesII(){
        devTools.createSession();
        devTools.send(Network.enable(
                Optional.empty(),
                Optional.empty(),
                Optional.empty()));
        devTools.send(org.openqa.selenium.devtools.v85.network.Network.emulateNetworkConditions(
                false,
                150,
                2500,
                2000,
                Optional.of(ConnectionType.CELLULAR3G)));
        driver.get("https://RexJones2.com");
        System.out.println("Enable slow Network : "+driver.getTitle());
    }

    @Test
    public void doNotEnableRexJonesII(){
        driver.get("https://RexJones2.com");
        System.out.println("Do not enable Network : "+driver.getTitle());
    }
}
