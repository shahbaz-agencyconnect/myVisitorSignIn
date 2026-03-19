package PageObjects;

import AbstractComponents.AbstractComponents;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class Merchandiser extends AbstractComponents {
    WebDriver driver;
    WebDriverWait wait;


    public Merchandiser(WebDriver driver) {
        super(driver);
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@placeholder='Search by Store ID or Store Name']")
    WebElement searchInput;

    @FindBy(xpath = "//button//span[text()='Search']")
    WebElement searchButton;

    @FindBy(xpath = " //a[text()='Merchandiser']")
    WebElement merchandiserTile;

    @FindBy(name = "email")
    WebElement emailInput;

    @FindBy(name = "contractorInductionNumber")
    WebElement inductionInput;

    @FindBy(xpath = "//span[text()='Verify']")
    WebElement verifyBtn;

    @FindBy(name = "merchandiserName")
    WebElement companyInput;

    @FindBy(name = "firstName")
    WebElement firstNameInput;

    @FindBy(name = "lastName")
    WebElement lastNameInput;

    @FindBy(name = "phoneNumber")
    WebElement phoneInput;

    // --- Date Picker ---
    @FindBy(xpath = "//button[@data-dates-input='true']")
    WebElement datePickerTrigger;

    @FindBy(css = ".mantine-DatePickerInput-calendarHeaderLevel")
    WebElement selectCurrentYear;

    @FindBy(xpath = "//span[text()='Privacy Policy']")
    WebElement privacyPolicyLink;

    // 2. The scrollable container inside the modal
    @FindBy(className = "mantine-Modal-body")
    WebElement modalBody;

    // 3. The 'I Agree' button
    @FindBy(xpath = "//button[.//span[text()='I Agree']]")
    WebElement iAgreeButton;

    // --- Checkboxes (Targeting the actual input element) ---
    @FindBy(xpath = "//span[text()='Site Detail Form']")
    WebElement siteDetailCheckbox;


    @FindBy(xpath = "//span[text()='Hazard Update Form']")
    WebElement hazardUpdateCheckbox;

    @FindBy(xpath = "//span[text()='Coles Wages & Conditions']")
    WebElement wagesCheckbox;

    @FindBy(xpath = "//span[text()='Sign in']")
    WebElement signInBtn;

    @FindBy(xpath = "//span[text()='Yes']")
    WebElement yesBtn;

    @FindBy(xpath = "//span[text()='No']")
    WebElement noBtn;

    @FindBy(xpath = "//span[text()='Take My Photo']")
    WebElement takePhotoBtn;


    public void merchandiserSigIn() throws InterruptedException {
        String storeId = "1023";
        String storeName = "Reliance";
        String email = "dusty.purdy@gmail.com";
        String inductionNumber = "254789";
        String company = "Reliance Merchandising";
        String fName = "Dusty";
        String lName = "Purdy";
        String phone = "0456789123";

        String xpath = "//button[contains(@class,'mantine-DatePickerInput-monthsListControl')]";
        String year = "2026";
        String month = "4";
        String day = "15";
        String date = String.format(
                "//button[contains(@class, 'mantine-DatePickerInput-day') and normalize-space()='%s' and not(@disabled) and not(@data-outside='true')]",
                day);
        String nextButton = "button.mantine-DatePickerInput-calendarHeaderControl[data-direction='next'] > svg";

        safeType(By.cssSelector("input[placeholder='Search by Store ID or Store Name']"), storeId);
        wait.until(ExpectedConditions.elementToBeClickable(searchButton));
        searchButton.click();
        selectStoreByName(storeName);
        waitForWebElementToAppear(merchandiserTile);
        merchandiserTile.click();
        wait.until(ExpectedConditions.visibilityOf(emailInput)).sendKeys(email);

        // Enter Induction Number
        inductionInput.sendKeys(inductionNumber);

        // Click Verify (Wait until it's enabled)
        wait.until(ExpectedConditions.elementToBeClickable(verifyBtn)).click();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".lucide-circle-check-big "))));

        // Fill text details
        wait.until(ExpectedConditions.visibilityOf(companyInput)).sendKeys(company);
        firstNameInput.sendKeys(fName);
        lastNameInput.sendKeys(lName);
        phoneInput.sendKeys(phone);

        datePickerTrigger.click();
        selectCurrentYear.click();
        selectCurrentYear.click();
        selectDate(xpath, year, month, date, nextButton);

        privacyPolicyLink.click();

        // Step 2: Wait for modal to be visible
        wait.until(ExpectedConditions.visibilityOf(modalBody));
        scrollToElement();
        wait.until(ExpectedConditions.elementToBeClickable(iAgreeButton)).click();

        siteDetailCheckbox.click();
        wait.until(ExpectedConditions.visibilityOf(modalBody));
        scrollToElement();
        wait.until(ExpectedConditions.elementToBeClickable(iAgreeButton)).click();

        hazardUpdateCheckbox.click();
        wait.until(ExpectedConditions.visibilityOf(modalBody));
        scrollToElement();
        wait.until(ExpectedConditions.elementToBeClickable(iAgreeButton)).click();

        wagesCheckbox.click();
        wait.until(ExpectedConditions.visibilityOf(modalBody));
        scrollToElement();
        wait.until(ExpectedConditions.elementToBeClickable(iAgreeButton)).click();

        signInBtn.click();

        // 2. Identify which modal appeared
// We use a small Wait to ensure the DOM has updated
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        By existingAccountHeader = By.xpath("//h2[text()='Account Already Exists']");
        By confirmationHeader = By.xpath("//h2[text()='New Sign In Confirmation']");
        By otpHeader = By.xpath("//div[contains(text(), 'Please enter the one time password')]");
        By changeDetailsHeader = By.xpath("//p[text()='Have any of your details changed?']");


        if (isElementPresent(existingAccountHeader)) {
            System.out.println("Flow: Existing Account Detected");
            // Click 'Yes' to sign in
            wait.until(ExpectedConditions.elementToBeClickable(yesBtn)).click();
            waitForWebElementToAppear(driver.findElement(By.xpath("//label[text()='Store']")));
//        wait.until(ExpectedConditions.elementToBeClickable(siteDetailCheckbox)).click();

            siteDetailCheckbox.click();
            wait.until(ExpectedConditions.visibilityOf(modalBody));
            scrollToElement();
            wait.until(ExpectedConditions.elementToBeClickable(iAgreeButton)).click();

            hazardUpdateCheckbox.click();
            wait.until(ExpectedConditions.visibilityOf(modalBody));
            scrollToElement();
            wait.until(ExpectedConditions.elementToBeClickable(iAgreeButton)).click();

            wagesCheckbox.click();
            wait.until(ExpectedConditions.visibilityOf(modalBody));
            scrollToElement();
            wait.until(ExpectedConditions.elementToBeClickable(iAgreeButton)).click();

            signInBtn.click();
            //Condition
            if (isElementPresent(changeDetailsHeader)) {
                System.out.println("Flow: Do you have any Change?");
                wait.until(ExpectedConditions.elementToBeClickable(noBtn)).click();
                waitForWebElementToAppear(driver.findElement(By.xpath("//label[text()='Inspection of Stock']")));
                driver.findElement(By.xpath("//label[text()='Inspection of Stock']")).click();
//            Thread.sleep(5000);
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
                takePhotoBtn.click();
            } else if (isElementPresent(confirmationHeader)) {
                System.out.println("Flow: Already Signed In");

                wait.until(ExpectedConditions.elementToBeClickable(yesBtn)).click();
                wait.until(ExpectedConditions.elementToBeClickable(noBtn)).click();
                waitForWebElementToAppear(driver.findElement(By.xpath("//label[text()='Inspection of Stock']")));
                driver.findElement(By.xpath("//label[text()='Inspection of Stock']")).click();
//            Thread.sleep(5000);
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
                takePhotoBtn.click();
            }
            // Add logic here to continue with the login password flow
        } else if (isElementPresent(confirmationHeader)) {
            System.out.println("Scenario 2: Already Logged In elsewhere");
            // Click 'Yes' to re-sign in (Confirming the new session)
            wait.until(ExpectedConditions.elementToBeClickable(yesBtn)).click();
            wait.until(ExpectedConditions.elementToBeClickable(noBtn)).click();
            waitForWebElementToAppear(driver.findElement(By.xpath("//label[text()='Inspection of Stock']")));
            driver.findElement(By.xpath("//label[text()='Inspection of Stock']")).click();
//            Thread.sleep(5000);
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
            takePhotoBtn.click();

        } else if (isElementPresent(otpHeader)) {
            System.out.println("Flow: OTP Verification Required");

            // Fetch your OTP (using the Twilio logic we discussed)
            String otp = "999999";

            // The PinInput has 6 separate input fields
            // We find all inputs inside the PinInput-root and fill them one by one
            List<WebElement> pinInputs = driver.findElements(By.cssSelector(".mantine-PinInput-input"));

            for (int i = 0; i < otp.length(); i++) {
                pinInputs.get(i).sendKeys(String.valueOf(otp.charAt(i)));
            }

            // Click Verify
            driver.findElement(By.xpath("//span[text()='Verify']")).click();
            waitForWebElementToAppear(driver.findElement(By.xpath("//label[text()='Inspection of Stock']")));
            driver.findElement(By.xpath("//label[text()='Inspection of Stock']")).click();
//            Thread.sleep(5000);
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
            takePhotoBtn.click();
        } else {
            throw new RuntimeException("Neither OTP nor Existing Account modal appeared!");
        }
    }

    public void selectStoreByName(String storeName) {
        // This finds the button that contains a div with your specific store name
        String xpath = String.format("//div[(text(), '%s')]]", storeName);

        WebElement storeButton = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//div[text()='" + storeName + "']"))));
        storeButton.click();
    }

    public void scrollToElement() throws InterruptedException {
        WebElement scrollable = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector(".rpv-core__inner-pages")
        ));

        JavascriptExecutor js = (JavascriptExecutor) driver;

        long lastScrollTop = -1;

        while (true) {

            // scroll down
            js.executeScript("arguments[0].scrollBy(0, 1800);", scrollable);
            Thread.sleep(500);

            // get current scroll position
            long currentScrollTop = (long) js.executeScript(
                    "return arguments[0].scrollTop", scrollable
            );

            // if no more scrolling possible → reached bottom
            if (currentScrollTop == lastScrollTop) {
                break;
            }

            lastScrollTop = currentScrollTop;
        }
    }

    public boolean isElementPresent(By locator) {
        // We use a short wait (e.g., 3-5 seconds) so we don't slow down the script
        try {
            new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.presenceOfElementLocated(locator));
            return driver.findElements(locator).size() > 0;
        } catch (Exception e) {
            return false;
        }
    }
}
