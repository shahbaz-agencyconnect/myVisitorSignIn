package AbstractComponents;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;
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

    public ArrayList<String> excelRead(String sheetName) {
        ArrayList<String> a = new ArrayList<>();
        String path = "H:\\ColesMyVisitor\\DataProvider\\DataSheets.xlsx";

        // Use try-with-resources to auto-close the file stream
        try (FileInputStream fis = new FileInputStream(path);
             XSSFWorkbook workbook = new XSSFWorkbook(fis)) {

            int sheets = workbook.getNumberOfSheets();
            for (int i = 0; i < sheets; i++) {
                if (workbook.getSheetName(i).equalsIgnoreCase(sheetName)) { // Ignore case for safety
                    XSSFSheet sheet = workbook.getSheetAt(i);
                    Iterator<Row> rows = sheet.iterator();

                    if (!rows.hasNext()) break; // Exit if sheet is completely empty
                    rows.next(); // Skip header row

                    while (rows.hasNext()) {
                        Row r = rows.next();
                        // Use a standard for-loop for cells to handle empty cells better
                        for (int cn = 0; cn < r.getLastCellNum(); cn++) {
                            Cell cell = r.getCell(cn, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);

                            if (cell.getCellType() == CellType.STRING) {
                                a.add(cell.getStringCellValue());
                            } else if (cell.getCellType() == CellType.NUMERIC) {
                                a.add(NumberToTextConverter.toText(cell.getNumericCellValue()));
                            } else {
                                a.add(""); // Add empty string for blank cells to keep list size consistent
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return a;
    }
}
