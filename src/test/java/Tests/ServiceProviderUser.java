package Tests;

import PageObjects.LandingPage;
import PageObjects.ServiceProviderUserManagement;
import TestComponents.BaseTest;
import org.testng.annotations.Test;

public class ServiceProviderUser extends BaseTest {
    @Test
    public void serviceProviderUser(){
        LandingPage lp = new LandingPage(driver);
        lp.loginApplication("ops_Manager", "1q2w3E*");
        ServiceProviderUserManagement serviceProvider = new ServiceProviderUserManagement(driver);
        serviceProvider.addServiceProviderUser();
        serviceProvider.editServiceProviderUser();
        serviceProvider.deleteServiceProviderUser();
    }
}
