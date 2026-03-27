package PageObjects;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Handles form filling operations for Merchandiser sign-in
 */
public class MerchandiserFormHandler {
    private final WebDriver driver;
    private final WebDriverWait wait;

    public MerchandiserFormHandler(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    /**
     * Fills the email field
     */
    public void fillEmail(WebElement emailInput, String email) {
        wait.until(ExpectedConditions.visibilityOf(emailInput)).sendKeys(email);
    }

    /**
     * Fills the induction number field
     */
    public void fillInductionNumber(WebElement inductionInput, String inductionNumber) {
        inductionInput.sendKeys(inductionNumber);
    }

    /**
     * Fills personal information (company, first name, last name, phone)
     */
    public void fillPersonalInfo(WebElement companyInput, WebElement firstNameInput,
                                  WebElement lastNameInput, WebElement phoneInput,
                                  String company, String fName, String lName, String phone) {
        wait.until(ExpectedConditions.visibilityOf(companyInput)).sendKeys(company);
        firstNameInput.sendKeys(fName);
        lastNameInput.sendKeys(lName);
        phoneInput.sendKeys("0" + phone);
    }

    /**
     * Searches for a store
     */
    public void searchStore(String storeId) {
        safeType(By.cssSelector("input[placeholder='Search by Store ID or Store Name']"), storeId);
    }

    /**
     * Safely types into a field
     */
    private void safeType(By locator, String text) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).sendKeys(text);
    }

    /**
     * Selects a store by name
     */
    public void selectStoreByName(String storeName) {
        String xpath = "//div[text()='" + storeName + "']";
        WebElement storeButton = wait.until(
                ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(xpath)))
        );
        storeButton.click();
    }

    /**
     * Clicks a button
     */
    public void clickButton(WebElement button) {
        wait.until(ExpectedConditions.elementToBeClickable(button)).click();
    }

    /**
     * Clicks and waits for visibility of an element
     */
    public void clickAndWait(WebElement button, By expectedElement) {
        clickButton(button);
        wait.until(ExpectedConditions.visibilityOfElementLocated(expectedElement));
    }
}

