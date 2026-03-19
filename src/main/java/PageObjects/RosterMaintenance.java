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

public class RosterMaintenance extends AbstractComponents {
    WebDriver driver;

    public RosterMaintenance(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[text()='Roster Management']")
    WebElement rosterMgmt;
    @FindBy(xpath = "//span[text()='Add Roster']")
    WebElement addRosterBtn;
    @FindBy(xpath = "//input[contains(@id,'mantine')]")
    WebElement rosterTemplate;
    @FindBy(css = ".mantine-CalendarHeader-calendarHeaderLevel")
    WebElement selectCurrentYear;

    @FindBy(xpath = "(//button[@data-dates-input='true'])[1]")
    WebElement startDateCalendar;

    @FindBy(xpath = "(//button[@data-dates-input='true'])[2]")
    WebElement endDateCalendar;

    @FindBy(xpath = "//span[text()='Publish Roster']")
    WebElement publishRoster;


    public void rosterByStore() throws InterruptedException {
        String monthFromXpath = "//table[contains(@class,'DatePickerInput-monthsList ')]//button";
        String fromYear = "2026";
        String fromMonth = "02";
        String fromDay = "2";
        String fromDate = String.format(
                "//button[contains(@class, 'mantine-DatePickerInput-day') and normalize-space()='%s' and not(@disabled) and not(@data-outside='true')]",
                fromDay
        );
        String toYear = "2026";
        String toMonth = "02";
        String toDay = "8";
        String toDate = String.format(
                "//button[contains(@class, 'mantine-DatePickerInput-day') and normalize-space()='%s' and not(@disabled) and not(@data-outside='true')]",
                toDay
        );
        String nextButton = "button.mantine-DatePickerInput-calendarHeaderControl[data-direction='next'] > svg";
        waitForWebElementToAppear(rosterMgmt);
        rosterMgmt.click();
        addRosterBtn.click();
        rosterTemplate.click();
        // 2. Wait for the option to be visible (it's often in a different part of the DOM)
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement option = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[contains(@class, 'mantine-Select-item') and text()='Geom Security Guard']")
        ));

        option.click();
        startDateCalendar.click();
        selectCurrentYear.click();
        selectCurrentYear.click();
        datePicker(monthFromXpath, fromYear, fromMonth, fromDate, nextButton);
        endDateCalendar.click();
        selectCurrentYear.click();
        selectCurrentYear.click();
        datePicker(monthFromXpath,toYear,toMonth,toDate,nextButton);
        publishRoster.click();
    }
}
