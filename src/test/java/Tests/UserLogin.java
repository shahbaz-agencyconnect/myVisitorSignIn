package Tests;

import PageObjects.LandingPage;
import TestComponents.BaseTest;
import org.testng.annotations.Test;

public class UserLogin extends BaseTest {
    @Test
    public void userLogin() {
        // The application is already launched by the @BeforeTest annotation in BaseTest
        // You can directly perform login actions here using landingPage object
        System.out.println("User login test is running");
        LandingPage lp = new LandingPage(driver);
        lp.loginApplication("ops_Manager", "1q2w3E*");
    }
}
