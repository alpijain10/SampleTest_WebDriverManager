package NewTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
//import static io.github.bonigarcia.wdm.WebDriverManager.*;

public class WMTest extends loginPage{
    WebDriver driver;
    @BeforeMethod
    public void setUp()
    {
//        String driverpath= "C:\\Program Files\\selenium-java-3.14\\Drivers\\ChromeDriver\\chromedriver.exe";
//        System.setProperty("webdriver.chrome.driver",driverpath);
//        driver=new ChromeDriver();

          WebDriverManager.chromedriver().setup();
          driver=new ChromeDriver();

    }
    @Test
    public void freeCRMTest()
    {
        driver.get("https://www.freecrm.com");
        System.out.println("Title is" +driver.getTitle());
    }

    @Test
    public void loginTest()
    {

    }


    @AfterMethod
    public void tearDown()
    {
        driver.quit();
    }
}
