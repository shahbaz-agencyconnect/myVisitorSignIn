package PageObjects;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

/**
 * Handles different sign-in scenarios/flows
 */
public class MerchandiserSignInFlow {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final MerchandiserUIInteraction uiInteraction;


    public MerchandiserSignInFlow(WebDriver driver, MerchandiserUIInteraction uiInteraction) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        this.uiInteraction = uiInteraction;

    }

    /**
     * Handles existing account flow
     */
    public void handleExistingAccountFlow(WebElement existingAccountBtnCall, WebElement siteDetailCheckbox,
                                          WebElement hazardUpdateCheckbox, WebElement wagesCheckbox,
                                          WebElement signInBtn, WebElement verifyBtn, WebElement takePhotoBtn,
                                          String reasonToVisit) throws InterruptedException {
        System.out.println("Flow: Existing Account Detected");

        // Click Yes to sign in
        wait.until(ExpectedConditions.elementToBeClickable(existingAccountBtnCall)).click();
        By storeForm = By.xpath("//label[text()='Store']");

        if (isElementPresent(storeForm)) {
            System.out.println("Already signed in before flow detected, handling accordingly...");
            wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(
                    By.xpath("//label[text()='Store']")
            )));
            // Accept all agreements
            uiInteraction.acceptAllAgreements(siteDetailCheckbox, hazardUpdateCheckbox, wagesCheckbox, signInBtn);
            //User can select Yes/No here for details change
            By detailsChangedCallBtn= By.xpath("//span[text()='No']");
            handleChangeDetailsFlow(detailsChangedCallBtn, verifyBtn, takePhotoBtn, reasonToVisit);
        } else {
            System.out.println("User selected not to Sign In");
        }

    }

    /**
     * Handles new sign-in confirmation flow
     */
    public void handleNewSignInConfirmationFlow(WebElement yesBtn, WebElement noBtn, WebElement verifyBtn,
                                                WebElement takePhotoBtn, String reasonToVisit) throws InterruptedException {
        System.out.println("Flow: Already Signed In at sometimes before");

        wait.until(ExpectedConditions.elementToBeClickable(yesBtn)).click();
        wait.until(ExpectedConditions.elementToBeClickable(noBtn)).click();

        selectReasonForVisit(reasonToVisit);
        handleOptionalSafetyForm(verifyBtn);
    }

    /**
     * Handles OTP verification flow
     */
    public void handleOTPFlow(String reasonToVisit, WebElement verifyBtn) throws InterruptedException {
        System.out.println("Flow: New Sign In with OTP Verification Required");

        String otp = "999999";
        fillOTPFields(otp);

        // Click Verify
        driver.findElement(By.xpath("//span[text()='Verify']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[text()='Please select the reason of your visit today?']")
        ));

        selectReasonForVisit(reasonToVisit);
        handleOptionalSafetyForm(verifyBtn);
    }

    /**
     * Selects reason for visit
     */
    private void selectReasonForVisit(String reasonToVisit) {
        driver.findElement(By.xpath("//label[text()='" + reasonToVisit + "']")).click();
    }

    /**
     * Handles optional safety form if present
     */
    private void handleOptionalSafetyForm(WebElement verifyBtn) throws InterruptedException {
        By safetyWorkMethod = By.xpath(
                "//label[text()='You have a valid Safety Work Method Statement and understand the carton movement notice']"
        );

        if (isElementPresent(safetyWorkMethod)) {
            wait.until(ExpectedConditions.elementToBeClickable(safetyWorkMethod)).click();
            verifyBtn.click();
            takeMyPhoto();
        } else {
            takeMyPhoto();
        }
    }

    /**
     * Handles existing account with details change question
     */
    public void handleChangeDetailsFlow(By updateBtn, WebElement verifyBtn, WebElement takePhotoBtn,
                                        String reasonToVisit) throws InterruptedException {
        System.out.println("Flow: Do you have any Change?");
        wait.until(ExpectedConditions.elementToBeClickable(updateBtn)).click();
        By companyLabel = By.xpath("//label[text()='Company']");
        if(isElementPresent(companyLabel)){
            System.out.println("Details change form appeared, filling details...");
//            driver.findElement(companyLabel).sendKeys(" Update");
            driver.findElement(By.name("firstName")).clear();
            driver.findElement(By.name("firstName")).sendKeys("John");
//            driver.findElement(By.xpath("//label[text()='Last Name']")).sendKeys("Doe");
//            driver.findElement(By.xpath("//label[text()='Phone Number']")).sendKeys("0123456789");
            driver.findElement(By.xpath("//span[text()='Update']")).click();
            handleOTPFlow(reasonToVisit, verifyBtn);

        } else {
            System.out.println("Details change form did not appear, proceeding...");
            selectReasonForVisit(reasonToVisit);
            handleOptionalSafetyForm(verifyBtn);
        }

    }

    /**
     * Fills OTP fields
     */
    private void fillOTPFields(String otp) {
        List<WebElement> pinInputs = driver.findElements(By.cssSelector(".mantine-PinInput-input"));
        for (int i = 0; i < otp.length(); i++) {
            pinInputs.get(i).sendKeys(String.valueOf(otp.charAt(i)));
        }
    }

    /**
     * Checks if element is present
     */
    private boolean isElementPresent(By locator) {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.presenceOfElementLocated(locator));
            return driver.findElements(locator).size() > 0;
        } catch (Exception e) {
            return false;
        }
    }

    public void takeMyPhoto() {
        By takePhotoBtn = By.xpath("//span[text()='Take My Photo']");
        wait.until(ExpectedConditions.elementToBeClickable(takePhotoBtn)).click();
//        takePhotoBtn.click();
        wait.until(driver -> {
            // JavaScript check:
            // readyState 4 = Have Enough Data (playing)
            // currentTime > 0 = The video has actually started moving
            Boolean isPlaying = (Boolean) ((JavascriptExecutor) driver).executeScript(
                    "return arguments[0].readyState === 4 && arguments[0].currentTime > 0;",
                    driver.findElement(By.cssSelector("video.size-\\[230px\\]"))
            );
            return isPlaying;
        });
        wait.until(ExpectedConditions.elementToBeClickable(takePhotoBtn)).click();
    }
}

