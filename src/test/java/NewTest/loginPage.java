package NewTest;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class loginPage {

    @FindBy(name="email")
    WebElement username;

    @FindBy(name="password")
    WebElement password;

    @FindBy(xpath="//div[contains(@class ,'submit')]")
    WebElement loginBtn;

}
