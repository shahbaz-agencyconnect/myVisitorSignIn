package PageObjects;

import AbstractComponents.AbstractComponents;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.ArrayList;

/**
 * REFACTORED Merchandiser Page Object
 * Uses helper classes for better maintainability and reusability
 */
public class MerchandiserRefactored extends AbstractComponents {
    WebDriver driver;
    WebDriverWait wait;
    private MerchandiserFormHandler formHandler;
    private MerchandiserUIInteraction uiInteraction;
    private MerchandiserSignInFlow signInFlow;
    private MerchandiserFlowDetector flowDetector;

    public MerchandiserRefactored(WebDriver driver) {
        super(driver);
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        PageFactory.initElements(driver, this);
        this.formHandler = new MerchandiserFormHandler(driver);
        this.uiInteraction = new MerchandiserUIInteraction(driver);
        this.signInFlow = new MerchandiserSignInFlow(driver, uiInteraction);
        this.flowDetector = new MerchandiserFlowDetector(driver);
    }

    // ============== PAGE ELEMENTS ==============
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

    @FindBy(xpath = "//button[@data-dates-input='true']")
    WebElement datePickerTrigger;

    @FindBy(css = ".mantine-DatePickerInput-calendarHeaderLevel")
    WebElement selectCurrentYear;

    @FindBy(xpath = "//span[text()='Privacy Policy']")
    WebElement privacyPolicyLink;

    @FindBy(className = "mantine-Modal-body")
    WebElement modalBody;

    @FindBy(xpath = "//button[.//span[text()='I Agree']]")
    WebElement iAgreeButton;

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

    @FindBy(xpath = "//span[text()='Update']")
    By updateBtn;

    @FindBy(xpath = "//span[text()='No']")
    WebElement noBtn;

    @FindBy(xpath = "//span[text()='Take My Photo']")
    WebElement takePhotoBtn;

    // ============== MAIN FLOW ==============

    /**
     * Main merchandiser sign-in flow - SIMPLIFIED
     */
    public void merchandiserSigIn() throws InterruptedException, FileNotFoundException {
        try {
            // Load data from Excel
            ArrayList<String> data = excelRead("Merchandiser");
            System.out.println("Loaded " + data.size() + " data elements from Excel");

            MerchandiserData merchData = new MerchandiserData(data);
            System.out.println("Created MerchandiserData: Store=" + merchData.getStoreName() +
                             ", Email=" + merchData.getEmail());

            // Execute main flow steps
            System.out.println("Step 1: Searching and selecting store...");
            searchAndSelectStore(merchData.getStoreId(), merchData.getStoreName());

            System.out.println("Step 2: Filling initial form...");
            fillInitialForm(merchData);

            System.out.println("Step 3: Handling privacy and agreements...");
            handlePrivacyAndAgreements();

            System.out.println("Step 4: Detecting and handling sign-in flow...");
            handleSignInFlow(merchData.getReasonToVisit());

            System.out.println("✅ Merchandiser sign-in completed successfully!");

        } catch (Exception e) {
            System.err.println("❌ Error during merchandiser sign-in: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * Searches for and selects a store
     */
    private void searchAndSelectStore(String storeId, String storeName) {
        formHandler.searchStore(storeId);
        wait.until(ExpectedConditions.elementToBeClickable(searchButton));
        searchButton.click();
        formHandler.selectStoreByName(storeName);
        waitForWebElementToAppear(merchandiserTile);
        merchandiserTile.click();
    }

    /**
     * Fills the initial user information form
     */
    private void fillInitialForm(MerchandiserData data) throws InterruptedException {
        formHandler.fillEmail(emailInput, data.getEmail());
        formHandler.fillInductionNumber(inductionInput, data.getInductionNumber());

        wait.until(ExpectedConditions.elementToBeClickable(verifyBtn)).click();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".lucide-circle-check-big"))));

        formHandler.fillPersonalInfo(companyInput, firstNameInput, lastNameInput, phoneInput,
                data.getCompany(), data.getFirstName(), data.getLastName(), data.getPhone());

        uiInteraction.selectDate(data.getDatePickerXpath(), data.getYear(),
                data.getMonth(), data.getDay(), data.getNextButton());
    }

    /**
     * Handles privacy policy and agreement acceptance
     */
    private void handlePrivacyAndAgreements() throws InterruptedException {
        privacyPolicyLink.click();
        wait.until(ExpectedConditions.visibilityOf(modalBody));
        uiInteraction.scrollToElementBottom();
        wait.until(ExpectedConditions.elementToBeClickable(iAgreeButton)).click();

        uiInteraction.acceptAllAgreements(siteDetailCheckbox, hazardUpdateCheckbox, wagesCheckbox, signInBtn);
    }

    /**
     * Detects the current flow type and executes appropriate handler
     */
    private void handleSignInFlow(String reasonToVisit) throws InterruptedException {
        // Action to be taken for  follow-up questions
        MerchandiserFlowDetector.SignInFlowType flowType = flowDetector.detectFlow();

        switch (flowType) {

            case OTP_VERIFICATION:
                signInFlow.handleOTPFlow(reasonToVisit, verifyBtn);
                break;

            case EXISTING_ACCOUNT:
                signInFlow.handleExistingAccountFlow(driver.findElement(By.xpath("//span[text()='Yes']")), siteDetailCheckbox,
                        hazardUpdateCheckbox, wagesCheckbox, signInBtn, verifyBtn, takePhotoBtn, reasonToVisit);
                break;

            case NEW_SIGN_IN_CONFIRMATION:
                signInFlow.handleNewSignInConfirmationFlow(yesBtn, noBtn, verifyBtn, takePhotoBtn, reasonToVisit);
                break;



            case CHANGE_DETAILS:
                signInFlow.handleChangeDetailsFlow(updateBtn, verifyBtn, takePhotoBtn, reasonToVisit);
                break;

            default:
                throw new RuntimeException("Unknown sign-in flow detected!");
        }
    }

    /**
     * Checks if element is present on the page
     */
    public boolean isElementPresent(By locator) {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.presenceOfElementLocated(locator));
            return driver.findElements(locator).size() > 0;
        } catch (Exception e) {
            return false;
        }
    }

    // ============== DATA CLASS ==============
    /**
     * Encapsulates Merchandiser sign-in data from Excel
     * Provides type-safe access to test data
     */
    class MerchandiserData {
        private final String storeId;
        private final String storeName;
        private final String email;
        private final String inductionNumber;
        private final String company;
        private final String firstName;
        private final String lastName;
        private final String phone;
        private final String year;
        private final String month;
        private final String day;
        private final String reasonToVisit;

        public MerchandiserData(ArrayList<String> data) {
            // Ensure we have at least 12 elements, pad with empty strings if needed
            while (data.size() < 12) {
                data.add("");
            }

            this.storeId = data.get(0);
            this.storeName = data.get(1);
            this.email = data.get(2);
            this.inductionNumber = data.get(3);
            this.company = data.get(4);
            this.firstName = data.get(5);
            this.lastName = data.get(6);
            this.phone = data.get(7);
            this.year = data.get(8);
            this.month = data.get(9);
            this.day = data.get(10);
            this.reasonToVisit = data.get(11);
        }

        public String getStoreId() { return storeId; }
        public String getStoreName() { return storeName; }
        public String getEmail() { return email; }
        public String getInductionNumber() { return inductionNumber; }
        public String getCompany() { return company; }
        public String getFirstName() { return firstName; }
        public String getLastName() { return lastName; }
        public String getPhone() { return phone; }
        public String getYear() { return year; }
        public String getMonth() { return month; }
        public String getDay() { return day; }
        public String getReasonToVisit() { return reasonToVisit; }
        public String getDatePickerXpath() {
            return "//button[contains(@class,'mantine-DatePickerInput-monthsListControl')]";
        }
        public String getNextButton() {
            return "button.mantine-DatePickerInput-calendarHeaderControl[data-direction='next'] > svg";
        }
    }
}
