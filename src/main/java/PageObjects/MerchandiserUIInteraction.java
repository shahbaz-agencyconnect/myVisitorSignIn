package PageObjects;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Handles repetitive UI interactions like accepting agreements
 */
public class MerchandiserUIInteraction {
    private final WebDriver driver;
    private final WebDriverWait wait;

    public MerchandiserUIInteraction(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        PageFactory.initElements(driver, this);
    }
    @FindBy(css = ".mantine-DatePickerInput-calendarHeaderLevel")
    WebElement selectCurrentYear;

    @FindBy(xpath = "//button[.//span[text()='I Agree']]")
    WebElement iAgreeButton;



    /**
     * Accepts a single agreement (checkbox + modal approval)
     */
    public void acceptSingleAgreement(WebElement checkbox, WebElement iAgreeButton) throws InterruptedException {
        checkbox.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("mantine-Modal-body")));
        scrollToElementBottom();
        wait.until(ExpectedConditions.elementToBeClickable(iAgreeButton)).click();
    }

    /**
     * Accepts all three agreements
     */
    public void acceptAllAgreements(WebElement siteDetailCheckbox, WebElement hazardUpdateCheckbox,
                                   WebElement wagesCheckbox, WebElement signInBtn) throws InterruptedException {
//        WebElement iAgreeButton = driver.findElement(By.xpath("//button[.//span[text()='I Agree']]"));

        acceptSingleAgreement(siteDetailCheckbox, iAgreeButton);
        acceptSingleAgreement(hazardUpdateCheckbox, iAgreeButton);
        acceptSingleAgreement(wagesCheckbox, iAgreeButton);

        signInBtn.click();
    }

    /**
     * Scrolls to the bottom of the modal
     */
    public void scrollToElementBottom() throws InterruptedException {
        WebElement scrollable = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector(".rpv-core__inner-pages")
        ));

        JavascriptExecutor js = (JavascriptExecutor) driver;
        long lastScrollTop = -1;

        while (true) {
            js.executeScript("arguments[0].scrollBy(0, 1800);", scrollable);
            Thread.sleep(500);

            long currentScrollTop = (long) js.executeScript("return arguments[0].scrollTop", scrollable);

            if (currentScrollTop == lastScrollTop) {
                break;
            }
            lastScrollTop = currentScrollTop;
        }
    }

    /**
     * Handles date picker selection
     */
    public void selectDate(String xpath, String year, String month, String day, String nextButton) throws InterruptedException {
        WebElement datePickerTrigger = driver.findElement(By.xpath("//button[@data-dates-input='true']"));
        datePickerTrigger.click();

//        WebElement selectCurrentYear = driver.findElement(By.cssSelector(".mantine-DatePickerInput-calendarHeaderLevel"));
        selectCurrentYear.click();
        selectCurrentYear.click();
        selectDateInternal(xpath, year, month, day, nextButton);
    }

    /**
     * Internal date selection logic
     */
    private void selectDateInternal(String xpath, String year, String month, String day, String nextButton) throws InterruptedException {
        // Year select
        boolean yearFound = false;
        while (!yearFound) {
            java.util.List<WebElement> years = driver.findElements(By.cssSelector(".mantine-DatePickerInput-yearsListControl"));
            for (WebElement yearElement : years) {
                if (yearElement.getText().equals(year)) {
                    yearElement.click();
                    yearFound = true;
                    break;
                }
            }
            if (!yearFound) {
                driver.findElement(By.cssSelector(nextButton)).click();
            }
        }

        // Month select
        driver.findElements(By.xpath(xpath)).get(Integer.parseInt(month) - 1).click();

        // Day select
        String dayXpath = String.format(
                "//button[contains(@class, 'mantine-DatePickerInput-day') and normalize-space()='%s' and not(@disabled) and not(@data-outside='true')]",
                day
        );
        driver.findElement(By.xpath(dayXpath)).click();
    }

    /**
     * Takes photo from the camera
     */
    public void takePhoto(WebElement takePhotoBtn) {
        takePhotoBtn.click();
        wait.until(driver -> {
            Boolean isPlaying = (Boolean) ((JavascriptExecutor) driver).executeScript(
                    "return arguments[0].readyState === 4 && arguments[0].currentTime > 0;",
                    driver.findElement(By.cssSelector("video.size-\\[230px\\]"))
            );
            return isPlaying;
        });
        takePhotoBtn.click();
    }
}

