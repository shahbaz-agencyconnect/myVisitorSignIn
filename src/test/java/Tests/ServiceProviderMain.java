package Tests;

import PageObjects.LandingPage;
import PageObjects.ServiceProvider;
import TestComponents.BaseTest;
import org.testng.annotations.Test;

public class ServiceProviderMain extends BaseTest {
    @Test
    public void serviceProviderMain() {
        LandingPage lp = new LandingPage(driver);
        lp.loginApplication("ops_Manager", "1q2w3E*");
        ServiceProvider serviceProvider = new ServiceProvider(driver);
        serviceProvider.addServiceProvider();
        serviceProvider.editServiceProvider();
        serviceProvider.deleteServiceProvider();
    }
}
