package PageObjects;

import AbstractComponents.AbstractComponents;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage extends AbstractComponents {
    WebDriver driver;

    public LandingPage(WebDriver driver) {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(id = "LoginInput_UserNameOrEmailAddress")
    WebElement username;

    @FindBy(id = "LoginInput_Password")
     WebElement userPssword;

    @FindBy(xpath = "//button[@type='submit']")
     WebElement btnLogin;

    public void goTo(String url) {
        driver.get(url);
    }

    public void loginApplication(String email, String password) {
        waitForWebElementToAppear(username);
        username.sendKeys(email);
        userPssword.sendKeys(password);
        btnLogin.click();
    }

}
