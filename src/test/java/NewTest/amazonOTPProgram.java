package NewTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import com.twilio.Twilio;
import com.twilio.base.ResourceSet;
import com.twilio.rest.api.v2010.account.Message;

import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
//TWILIO API USAGE
public class amazonOTPProgram {

    public static WebDriver driver;
    public static final String ACCOUNT_SID="AC7736fbf2c397e3910c886f036af3634b";
    public static final String ACCOUNT_TKN="8048bfdf51804d10731e64e6958545ec";
// +16028991217

    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        JavascriptExecutor js= (JavascriptExecutor)driver;
        js.executeScript("alert('Welcome to learning')");


        driver=new ChromeDriver();
        driver.get("https://www.amazon.in");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.MILLISECONDS);
        driver.findElement(By.xpath("//span[text()='Account & Lists']")).click();
        driver.findElement(By.xpath("//a[@id='createAccountSubmit']")).click();
        driver.findElement(By.xpath("//input[@id='ap_customer_name']")).sendKeys("alpijain79@gmail.com");
        driver.findElement(By.id("auth-country-picker-container")).click();
        driver.findElement(By.xpath("//ul[@role='application']/li/a[contains(text(),'United States +1')]")).click();
        driver.findElement(By.xpath("//input[@placeholder='Mobile number']")).sendKeys("6028991217");
        driver.findElement(By.xpath("//input[@id='ap_password']")).sendKeys("alpi9999");
        driver.findElement(By.xpath("//input[@id='continue']")).click();
       // driver.findElement(By.xpath("//input[@type='tel']")).sendKeys();

        //get OTP from twilio
        Twilio.init(ACCOUNT_SID,ACCOUNT_TKN);
        String smsBody = getMessage();
        System.out.println(smsBody);
        String OTPNumber = smsBody.replaceAll("[^-?0-9]+", " ");
        System.out.println(OTPNumber);

        driver.findElement(By.id("auth-pv-enter-code")).sendKeys(OTPNumber);

    }

    public static String getMessage() {
        return getMessages().filter(m -> m.getDirection().compareTo(Message.Direction.INBOUND) == 0)
                .filter(m -> m.getTo().equals("+16028991217")).map(Message::getBody).findFirst()
                .orElseThrow(IllegalStateException::new);
    }

    private static Stream<Message> getMessages() {
        ResourceSet<Message> messages = Message.reader(ACCOUNT_SID).read();
        return StreamSupport.stream(messages.spliterator(), false);
    }
}
