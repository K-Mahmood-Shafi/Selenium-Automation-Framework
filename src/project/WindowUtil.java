package project;

import java.util.Set;

import org.openqa.selenium.WebDriver;

public class WindowUtil {

    private WindowUtil() {
    }

    public static void switchToWindowByTitle(WebDriver driver, String expectedTitle) {
        String current = driver.getWindowHandle();
        Set<String> windows = driver.getWindowHandles();

        for (String window : windows) {
            driver.switchTo().window(window);
            if (driver.getTitle().contains(expectedTitle)) {
                return;
            }
        }
        driver.switchTo().window(current);
    }

    public static void switchToChildWindow(WebDriver driver) {
        String parent = driver.getWindowHandle();
        for (String window : driver.getWindowHandles()) {
            if (!window.equals(parent)) {
                driver.switchTo().window(window);
                break;
            }
        }
    }

    public static void closeAllChildWindows(WebDriver driver) {
        String parent = driver.getWindowHandle();
        for (String window : driver.getWindowHandles()) {
            if (!window.equals(parent)) {
                driver.switchTo().window(window);
                driver.close();
            }
        }
        driver.switchTo().window(parent);
    }
}

