package AbstractComponents;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class AbstractComponents {
    WebDriver driver;

    public AbstractComponents(WebDriver driver) {
        this.driver = driver;
    }
    public void waitForWebElementToAppear(WebElement findBy) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOf(findBy));
    }

    // Date Picker
    public void datePicker(String xPath, String selectYear, String selectMonth, String selectDay, String nextButton)
            throws InterruptedException {

        // Year select

        while (true) {

            List<WebElement> getyears = driver.findElements(By.cssSelector(".mantine-DatePickerInput-pickerControl"));
            boolean yearFound = false;

            for (WebElement yearElement : getyears) {
                String year = yearElement.getText();
                if (year.equals(selectYear) || year.equals(null)) {
                    yearElement.click();
                    yearFound = true;
                    break;
                }
            }
            if (yearFound) {
                break;
            } else {
                WebElement nextYearBtn = driver.findElement(By.cssSelector(nextButton));
                nextYearBtn.click();
//				Thread.sleep(1000);
            }
        }

        // Month select
        driver.findElements(By.xpath(xPath)).get(Integer.parseInt(selectMonth) - 1).click();

        // Days select
        driver.findElement(By.xpath(selectDay)).click();
//		Thread.sleep(1000);
    }

    // Date Picker
    public void selectDate(String xPath, String selectYear, String selectMonth, String selectDay, String nextButton)
            throws InterruptedException {

        // Year select

        while (true) {

            List<WebElement> getyears = driver.findElements(By.cssSelector(".mantine-DatePickerInput-yearsListControl"));
            boolean yearFound = false;

            for (WebElement yearElement : getyears) {
                String year = yearElement.getText();
                if (year.equals(selectYear) || year.equals(null)) {
                    yearElement.click();
                    yearFound = true;
                    break;
                }
            }
            if (yearFound) {
                break;
            } else {
                WebElement nextYearBtn = driver.findElement(By.cssSelector(nextButton));
                nextYearBtn.click();
//				Thread.sleep(1000);
            }
        }

        // Month select
        driver.findElements(By.xpath(xPath)).get(Integer.parseInt(selectMonth) - 1).click();

        // Days select
        driver.findElement(By.xpath(selectDay)).click();
//		Thread.sleep(1000);
    }
    public void safeType(By locator, String text) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        int attempts = 0;
        while (attempts < 3) {
            try {
                // 1. Wait for visibility
                WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
                // 2. Clear and type
                element.clear();
                element.sendKeys(text);
                return; // Success! Exit the method.
            } catch (StaleElementReferenceException e) {
                System.out.println("Element went stale, retrying... Attempt: " + (attempts + 1));
                attempts++;
            }
        }
        throw new RuntimeException("Could not interact with element after 3 attempts due to staleness.");
    }
}
