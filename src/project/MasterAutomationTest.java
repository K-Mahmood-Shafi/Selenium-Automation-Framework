package project;

import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MasterAutomationTest extends BaseTest {

    // 1. Locators + basic login / form
    @Test(priority = 1)
    public void testLocatorsAndLogin() {
        driver.get("https://rahulshettyacademy.com/locatorspractice/");

        driver.findElement(By.id("inputUsername")).sendKeys("rahul");
        driver.findElement(By.name("inputPassword")).sendKeys("learning");
        driver.findElement(By.className("signInBtn")).click();

        WebElement successMsg = WaitUtil.waitForVisible(driver, By.cssSelector("p.error"), 5);
        System.out.println("Login message: " + successMsg.getText());

        driver.findElement(By.linkText("Forgot your password?")).click();

        WaitUtil.waitForVisible(driver, By.xpath("//input[@placeholder='Name']"), 5).sendKeys("Mahmood");
        driver.findElement(By.cssSelector("input[placeholder='Email']")).sendKeys("test@test.com");
        driver.findElement(By.cssSelector("input[placeholder='Phone Number']")).sendKeys("1234567890");

        driver.findElement(By.cssSelector(".reset-pwd-btn")).click();
        WebElement infoMsg = WaitUtil.waitForVisible(driver, By.cssSelector("p.infoMsg"), 5);
        System.out.println("Reset message: " + infoMsg.getText());

        driver.findElement(By.cssSelector(".go-to-login-btn")).click();
    }

    // 2. Static dropdown using Select
    @Test(priority = 2)
    public void testStaticDropdown() {
        driver.get("https://rahulshettyacademy.com/dropdownsPractise/");

        WebElement currencyDropdown = driver.findElement(By.id("ctl00_mainContent_DropDownListCurrency"));
        DropDownUtil.selectByVisibleText(currencyDropdown, "INR");
        DropDownUtil.selectByIndex(currencyDropdown, 2);

        Select select = new Select(currencyDropdown);
        String selected = select.getFirstSelectedOption().getText();
        System.out.println("Selected currency: " + selected);
        Assert.assertEquals(selected, "USD"); // this will change based on index you choose
    }

    // 3. Dynamic dropdown
    @Test(priority = 3)
    public void testDynamicDropdown() {
        driver.get("https://rahulshettyacademy.com/dropdownsPractise/");

        driver.findElement(By.id("ctl00_mainContent_ddl_originStation1_CTXT")).click();
        DropDownUtil.selectDynamicOptionByText(driver,
                By.cssSelector("a[value='BLR']"),
                "Bengaluru (BLR)");

        WaitUtil.sleep(1000);

        DropDownUtil.selectDynamicOptionByText(driver,
                By.xpath("//div[@id='glsctl00_mainContent_ddl_destinationStation1_CTNR']//a[@value='MAA']"),
                "Chennai (MAA)");
    }

    // 4. Auto-suggestive dropdown
    @Test(priority = 4)
    public void testAutoSuggestiveDropdown() {
        driver.get("https://rahulshettyacademy.com/dropdownsPractise/");

        By inputLocator = By.id("autosuggest");
        By suggestionLocator = By.cssSelector("li.ui-menu-item div");

        DropDownUtil.selectFromAutoSuggest(driver, inputLocator, suggestionLocator, "ind", "India");
        String value = driver.findElement(inputLocator).getAttribute("value");
        System.out.println("Chosen country: " + value);
        Assert.assertEquals(value, "India");
    }

    // 5. Actions – hover, drag-drop etc.
    @Test(priority = 5)
    public void testActions() {
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");

        WebElement mouseHoverBtn = driver.findElement(By.id("mousehover"));
        ActionsUtil.hoverOverElement(driver, mouseHoverBtn);

        WebElement topOption = driver.findElement(By.linkText("Top"));
        topOption.click();

        // Example drag and drop demo (different site)
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://demoqa.com/droppable");

        WebElement source = driver.findElement(By.id("draggable"));
        WebElement target = driver.findElement(By.id("droppable"));

        ActionsUtil.dragAndDrop(driver, source, target);
        Assert.assertTrue(target.getText().contains("Dropped!"));
    }

    // 6. Alerts handling
    @Test(priority = 6)
    public void testAlerts() {
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");

        driver.findElement(By.id("alertbtn")).click();
        String alertText = AlertUtil.getAlertText(driver);
        System.out.println("Alert text: " + alertText);
        AlertUtil.acceptAlert(driver);

        driver.findElement(By.id("name")).sendKeys("Mahmood");
        driver.findElement(By.id("confirmbtn")).click();
        String confirmText = AlertUtil.getAlertText(driver);
        System.out.println("Confirm text: " + confirmText);
        AlertUtil.dismissAlert(driver);
    }

    // 7. Frames
    @Test(priority = 7)
    public void testFrames() {
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");

        WebElement frameElement = driver.findElement(By.id("courses-iframe"));
        FrameUtil.switchToFrameByElement(driver, frameElement);

        WebElement blogLink = driver.findElement(By.linkText("Blog"));
        blogLink.click();

        FrameUtil.switchToDefaultContent(driver);
        System.out.println("Frame test completed");
    }

    // 8. Window handling
    @Test(priority = 8)
    public void testWindowHandling() {
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");

        String parentWindow = driver.getWindowHandle();
        driver.findElement(By.id("openwindow")).click();

        WindowUtil.switchToChildWindow(driver);
        System.out.println("Child window title: " + driver.getTitle());

        driver.close(); // close child
        driver.switchTo().window(parentWindow);
        System.out.println("Back to parent window: " + driver.getTitle());

        // open tab
        driver.findElement(By.id("opentab")).click();
        Set<String> handles = driver.getWindowHandles();
        System.out.println("Total windows/tabs: " + handles.size());
        WindowUtil.closeAllChildWindows(driver);
    }

    // 9. Scrolling + table handling
    @Test(priority = 9)
    public void testScrollingAndTable() {
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");

        WebElement table = driver.findElement(By.id("product"));
        ScrollUtil.scrollToElement(driver, table);

        List<WebElement> rows = table.findElements(By.tagName("tr"));
        System.out.println("Table row count: " + rows.size());
    }

    // 10. Cookies & simple SSL site check
    @Test(priority = 10)
    public void testCookiesAndSSL() {
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");

        Cookie cookie = new Cookie("testCookie", "12345");
        driver.manage().addCookie(cookie);

        Set<Cookie> allCookies = driver.manage().getCookies();
        System.out.println("Total cookies: " + allCookies.size());

        driver.manage().deleteCookie(cookie);
        System.out.println("Cookie deleted, total now: " + driver.manage().getCookies().size());

        // SSL example – will pass if page loads fine
        driver.get("https://expired.badssl.com/");
        System.out.println("SSL page title: " + driver.getTitle());
    }

    // 11. Screenshot
    @Test(priority = 11)
    public void testScreenshot() {
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");
        String path = ScreenshotUtil.takeScreenshot(driver, "AutomationPracticeHome");
        System.out.println("Screenshot saved at: " + path);
    }

    // 12. Calendar handling
    @Test(priority = 12)
    public void testCalendar() {
        driver.get("https://www.path2usa.com/travel-companion/");

        // Scroll to calendar section
        ScrollUtil.scrollByPixels(driver, 0, 800);

        By calendarInput = By.id("form-field-travel_comp_date");
        driver.findElement(calendarInput).click();

        By monthYear = By.cssSelector(".flatpickr-current-month .cur-month");
        By yearLocator = By.cssSelector(".numInput.cur-year");
        By nextButton = By.cssSelector(".flatpickr-next-month");
        By dayCells = By.cssSelector(".flatpickr-day:not(.prevMonthDay):not(.nextMonthDay)");

        // Here we do a simple approach: change year field + month loop
        driver.findElement(yearLocator).clear();
        driver.findElement(yearLocator).sendKeys("2025");

        // Use CalendarUtil generic selector (adjusted for month text only)
        CalendarUtil.selectDate(driver,
                By.cssSelector(".flatpickr-current-month .cur-month"),
                nextButton,
                dayCells,
                "July",
                "2025",
                "15");
    }

    // 13. E2E UI practice – small checkout flow
    @Test(priority = 13)
    public void testE2ECheckout() {
        driver.get("https://rahulshettyacademy.com/seleniumPractise/#/");

        // Search and add items
        driver.findElement(By.cssSelector("input.search-keyword")).sendKeys("ca");
        WaitUtil.sleep(2000);

        List<WebElement> products = driver.findElements(By.cssSelector("h4.product-name"));
        List<WebElement> addToCartButtons = driver.findElements(By.xpath("//div[@class='product-action']/button"));

        for (int i = 0; i < products.size(); i++) {
            String name = products.get(i).getText().toLowerCase();
            if (name.contains("carrot") || name.contains("capsicum")) {
                addToCartButtons.get(i).click();
            }
        }

        driver.findElement(By.cssSelector("img[alt='Cart']")).click();
        driver.findElement(By.xpath("//button[text()='PROCEED TO CHECKOUT']")).click();

        WaitUtil.waitForVisible(driver, By.cssSelector("input.promoCode"), 5).sendKeys("rahulshettyacademy");
        driver.findElement(By.cssSelector("button.promoBtn")).click();

        WebElement promoInfo = WaitUtil.waitForVisible(driver, By.cssSelector("span.promoInfo"), 10);
        System.out.println("Promo message: " + promoInfo.getText());

        driver.findElement(By.xpath("//button[text()='Place Order']")).click();
        WebElement countryDropdown = driver.findElement(By.tagName("select"));
        DropDownUtil.selectByVisibleText(countryDropdown, "India");

        driver.findElement(By.cssSelector("input.chkAgree")).click();
        driver.findElement(By.xpath("//button[text()='Proceed']")).click();

        Assert.assertTrue(driver.getPageSource().contains("Thank you"), "Order not completed successfully");
    }
}

