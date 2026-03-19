package Tests;

import PageObjects.LandingPage;
import PageObjects.Merchandiser;
import TestComponents.BaseTest;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class MerchandiserSIgIn extends BaseTest {
    @Test
    public void loginMerchandiser() throws IOException, InterruptedException {
        Properties prop = new Properties();
        FileInputStream fip =new FileInputStream(System.getProperty("user.dir")+"//src//main//java//Resources//GlobalData.properties");
        prop.load(fip);
        String url=prop.getProperty("visitorsUrl");
        landingPage.goTo(url);
        Merchandiser merchandiser = new Merchandiser(driver);
        merchandiser.merchandiserSigIn();
    }

}
