package project;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CalendarUtil {

    private CalendarUtil() {
    }

    public static void selectDate(WebDriver driver,
                                  By monthYearLocator,
                                  By nextButtonLocator,
                                  By dayCellsLocator,
                                  String expectedMonth,
                                  String expectedYear,
                                  String day) {

        while (true) {
            String[] parts = driver.findElement(monthYearLocator).getText().split(" ");
            String month = parts[0].trim();
            String year = parts[1].trim();

            if (month.equalsIgnoreCase(expectedMonth) && year.equalsIgnoreCase(expectedYear)) {
                break;
            }
            driver.findElement(nextButtonLocator).click();
            WaitUtil.sleep(500);
        }

        List<WebElement> dates = driver.findElements(dayCellsLocator);
        for (WebElement d : dates) {
            if (d.getText().equals(day)) {
                d.click();
                break;
            }
        }
    }
}

