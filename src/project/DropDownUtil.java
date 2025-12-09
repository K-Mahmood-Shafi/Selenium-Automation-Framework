package project;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class DropDownUtil {

    private DropDownUtil() {
    }

    public static void selectByVisibleText(WebElement element, String text) {
        new Select(element).selectByVisibleText(text);
    }

    public static void selectByIndex(WebElement element, int index) {
        new Select(element).selectByIndex(index);
    }

    public static void selectByValue(WebElement element, String value) {
        new Select(element).selectByValue(value);
    }

    public static void selectDynamicOptionByText(WebDriver driver, By optionsLocator, String textToSelect) {
        List<WebElement> options = driver.findElements(optionsLocator);
        for (WebElement option : options) {
            if (option.getText().trim().equalsIgnoreCase(textToSelect.trim())) {
                option.click();
                break;
            }
        }
    }

    public static void selectFromAutoSuggest(WebDriver driver, By inputLocator, By suggestionLocator, String textToType,
            String finalSelection) {

        WebElement input = driver.findElement(inputLocator);
        input.clear();
        input.sendKeys(textToType);

        WaitUtil.sleep(1000);

        List<WebElement> suggestions = driver.findElements(suggestionLocator);
        for (WebElement suggestion : suggestions) {
            if (suggestion.getText().trim().equalsIgnoreCase(finalSelection.trim())) {
                suggestion.click();
                return;
            }
        }

        input.sendKeys(Keys.ENTER);
    }
}

