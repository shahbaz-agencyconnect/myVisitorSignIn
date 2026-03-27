package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Detects which sign-in flow should be executed
 */
public class MerchandiserFlowDetector {
    private final WebDriver driver;
    private final WebDriverWait wait;

    public enum SignInFlowType {
        EXISTING_ACCOUNT,
        NEW_SIGN_IN_CONFIRMATION,
        OTP_VERIFICATION,
        CHANGE_DETAILS,
        UNKNOWN
    }

    public MerchandiserFlowDetector(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    /**
     * Detects the current sign-in flow
     */
    public SignInFlowType detectFlow() {

        //New Sign In using OTP verification
        if (isElementPresent(By.xpath("//div[contains(text(), 'Please enter the one time password')]"))) {
            return SignInFlowType.OTP_VERIFICATION;
        }

        // Existing account flow
        if (isElementPresent(By.xpath("//h2[text()='Account Already Exists']"))) {
            return SignInFlowType.EXISTING_ACCOUNT;
        }

        if (isElementPresent(By.xpath("//h2[text()='New Sign In Confirmation']"))) {
            return SignInFlowType.NEW_SIGN_IN_CONFIRMATION;
        }

        if (isElementPresent(By.xpath("//p[text()='Have any of your details changed?']"))) {
            return SignInFlowType.CHANGE_DETAILS;
        }

        return SignInFlowType.UNKNOWN;
    }

    /**
     * Checks if element is present on the page
     */
    private boolean isElementPresent(By locator) {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            return driver.findElements(locator).size() > 0;
        } catch (Exception e) {
            return false;
        }
    }
}

