package project;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FrameUtil {

    private FrameUtil() {
    }

    public static void switchToFrameByIndex(WebDriver driver, int index) {
        driver.switchTo().frame(index);
    }

    public static void switchToFrameByNameOrId(WebDriver driver, String nameOrId) {
        driver.switchTo().frame(nameOrId);
    }

    public static void switchToFrameByElement(WebDriver driver, WebElement frameElement) {
        driver.switchTo().frame(frameElement);
    }

    public static void switchToDefaultContent(WebDriver driver) {
        driver.switchTo().defaultContent();
    }
}
