package Tests;

import PageObjects.LandingPage;
import PageObjects.RosterMaintenance;
import TestComponents.BaseTest;
import org.testng.annotations.Test;

public class RosterManagement extends BaseTest {
    @Test
    public void manageRoster() throws InterruptedException {
        LandingPage lp = new LandingPage(driver);
        lp.loginApplication("ops_Manager", "1q2w3E*");
        RosterMaintenance roster = new RosterMaintenance(driver);
        roster.rosterByStore();
    }
}
