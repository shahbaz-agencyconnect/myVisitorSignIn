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
import java.util.List;

public class ServiceProviderUserManagement extends AbstractComponents {
    WebDriver driver;

    public ServiceProviderUserManagement(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[text()='Configurations']")
    WebElement configurations;

    @FindBy(xpath = "//a[text()='Service Providers']")
    WebElement serviceProviderUserMenu;

    @FindBy(xpath = "//span[text()='Add']")
    WebElement addBtn;

    @FindBy(name = "name")
    WebElement nameField;

    @FindBy(name = "address")
    WebElement addressField;

    @FindBy(xpath = "//div[input[@name='visitorType']]//input[@type='search']")
    WebElement visitorTypeDropdown;

    @FindBy(xpath = "//div[contains(@class, 'mantine-Select-item') and text()='Security Guard']")
    WebElement selectVisitorType;

    @FindBy(xpath = "//span[text()='Save']")
    WebElement saveBtn;

    @FindBy(xpath = "//span[text()='Confirm']")
    WebElement confirmBtn;

    public void addServiceProviderUser() {
        waitForWebElementToAppear(configurations);
        configurations.click();
        serviceProviderUserMenu.click();
        addBtn.click();
        nameField.sendKeys("Tata Safari");
        addressField.sendKeys("98 Shirley Street, Pimpama QLD 4209");
        visitorTypeDropdown.click();
        selectVisitorType.click();
        saveBtn.click();
    }

    public void editServiceProviderUser() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        boolean isFound = false;

        while (!isFound) {
            // 1. Try to find the element on the current page
            List<WebElement> rows = driver.findElements(By.xpath("//div[@role='row' and .//div[@col-id='name' and text()='Tata Safari']]"));

            if (!rows.isEmpty()) {
                // 2. Element found! Click the edit button within this row
                // We use the edit button class 'mantine-dbph30' found in your HTML
                WebElement editButton = rows.get(0).findElement(By.xpath(".//div[@col-id='actions']//button[contains(@class, 'mantine-dbph30')]"));

                wait.until(ExpectedConditions.elementToBeClickable(editButton)).click();
                isFound = true;
                break;
            }
//            if (isFound){
//                break;
//            }
            else {
                WebElement nextBtn = driver.findElement(By.cssSelector(".ag-icon-next"));
                nextBtn.click();
            }
        }
        nameField.sendKeys(" Update");
        saveBtn.click();
    }

    public void deleteServiceProviderUser() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        boolean isFound = false;
        while (!isFound) {
            List<WebElement> rows = driver.findElements(By.xpath("//div[@role='row' and .//div[@col-id='name' and text()='Tata Safari Update']]"));

            if (!rows.isEmpty()) {
                // 2. Element found! Click the edit button within this row
                // We use the edit button class 'mantine-dbph30' found in your HTML
                WebElement deleteButton = rows.get(0).findElement(By.xpath(".//div[@col-id='actions']//button[contains(@class, 'mantine-1flu9xd')]"));

                wait.until(ExpectedConditions.elementToBeClickable(deleteButton)).click();
                isFound = true;
                break;
            } else {
                System.out.println("Service Provider not found");
            }
        }
        confirmBtn.click();
    }
}
