package Tests;

import PageObjects.LandingPage;
import PageObjects.StoreInformation;
import TestComponents.BaseTest;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class StoreManagement extends BaseTest {
    @Test
    public void manageStore() throws IOException {
        Properties prop = new Properties();
        FileInputStream fip = new FileInputStream(System.getProperty("user.dir") + "//src//main//java//Resources//GlobalData.properties");
        prop.load(fip);
        String url = System.getProperty("adminUrl");
        landingPage.goTo(url);
        landingPage.loginApplication("ops_Manager", "1q2w3E*");
        StoreInformation storeInformation = new StoreInformation(driver);
        storeInformation.addStore();
    }
}
