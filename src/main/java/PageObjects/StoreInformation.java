package PageObjects;

import AbstractComponents.AbstractComponents;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class StoreInformation extends AbstractComponents {
    WebDriver driver;

    public StoreInformation(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[text()='Configurations']")
    WebElement configurations;

    @FindBy(xpath = "//a[text()='Store Information']")
    WebElement storeInfoMenu;

    @FindBy(xpath = "//span[text()='Add']")
    WebElement addBtn;

    @FindBy(xpath = "//label[contains(text(),'State')]/following-sibling::div//input[@type='search']")
    WebElement stateDropdownTrigger;

    @FindBy(name = "storeId")
    WebElement inputStoreId;

    @FindBy(name = "name")
    WebElement inputStoreName;

    @FindBy(name = "contactNumber")
    WebElement inputPhone;

    @FindBy(name = "contactEmail")
    WebElement inputEmail;

    @FindBy(name = "address")
    WebElement inputAddress;

    @FindBy(name = "region")
    WebElement inputRegion;

    @FindBy(name = "longitude")
    WebElement inputLongitude;

    @FindBy(name = "latitude")
    WebElement inputLatitude;

    @FindBy(name = "distanceThreshold")
    WebElement inputDistanceThreshold;

    @FindBy(name = "openTime")
    WebElement inputOpenTime;

    @FindBy(name = "closeTime")
    WebElement inputCloseTime;

    @FindBy(xpath = "(//input[@type='file'])[1]")
    WebElement fileSiteDetail;

    @FindBy(xpath = "(//input[@type='file'])[2]")
    WebElement fileHazardUpdate;

    @FindBy(xpath = "(//input[@type='file'])[3]")
    WebElement fileWagesConditions;

    @FindBy(name = "isHazardType")
    WebElement checkboxHazard;

    @FindBy(xpath = "(//input[@type='file'])[4]")
    WebElement fileSolarPanel;

    @FindBy(xpath = "(//input[@type='file'])[5]")
    WebElement fileHazardousMaterial;

    @FindBy(xpath = "//button[@type='submit']")
    WebElement btnSave;

    public void addStore() {
        configurations.click();
        storeInfoMenu.click();
        addBtn.click();
        stateDropdownTrigger.click();
        // Options are usually generated dynamically in the DOM
        String optionXpath = "//div[@role='option' or contains(@class,'Select-item')][contains(text(),'Queensland')]";
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement option = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(optionXpath)));
        option.click();
        inputStoreId.sendKeys("1024");
        inputStoreName.sendKeys("Brisbane Store");
        inputPhone.sendKeys("0444578521");
        inputEmail.sendKeys("brisbane_store23@hotmail.com");
        inputAddress.sendKeys("1835 Beechmont Rd, Beechmont QLD 4211, Australia");
        inputRegion.sendKeys("South East Queensland");
        inputLongitude.sendKeys("153.12345");
        inputLatitude.sendKeys("-27.12345");
        inputDistanceThreshold.sendKeys("500");
        inputOpenTime.sendKeys("10:00AM");
        inputCloseTime.sendKeys("07:00PM");
        String filePath = "C:\\Users\\khans\\Desktop\\Test Sample Files\\sample.pdf";

        fileSiteDetail.sendKeys(filePath);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='Site Detail Form']/parent::div//button//div[text()='sample.pdf']")));
        fileHazardUpdate.sendKeys(filePath);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='Hazard Update Form']/parent::div//button//div[text()='sample.pdf']")));

        fileWagesConditions.sendKeys(filePath);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='Coles Wages & Conditions']/parent::div//button//div[text()='sample.pdf']")));

        checkboxHazard.click();
        fileSolarPanel.sendKeys(filePath);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='Solar Panel']/parent::div//button//div[text()='sample.pdf']")));
        fileHazardousMaterial.sendKeys(filePath);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='Hazardous Materials']/parent::div//button//div[text()='sample.pdf']")));
        btnSave.click();
    }
}
