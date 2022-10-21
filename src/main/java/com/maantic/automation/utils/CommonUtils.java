package com.maantic.automation.utils;

import com.maantic.automation.base.BasePage;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CommonUtils extends BasePage {

    public static void enterText(By locator, String input) {
        WebElement ele = getDriver().findElement(locator);
        if (ele.isDisplayed()) {
            ele.clear();
            ele.sendKeys(input);
        } else {
            System.out.println("Element not found.");
        }
    }

    public static List<WebElement> getElements(By locator){
        return getDriver().findElements(locator);
    }

    public static void scrollDownPage(int pixel){
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("window.scrollBy(0,"+pixel+")", "");
    }
    public static WebElement getElement(By locator) {
        try {
            WebElement element = getDriver().findElement(locator);
            return element;
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public static void click(By locator) {
        WebElement ele = getDriver().findElement(locator);
        if (ele.isDisplayed()) {
            ele.click();
        } else {
            System.out.println("Element is not found.");
        }
    }

    public static void clickByXpath(By locator) {
        WebElement ele = getDriver().findElement(locator);
        if (ele.isDisplayed()) {
            ele.click();
        } else {
            System.out.println("Element is not found.");
        }
    }

    public static boolean isElementPresent(By locator) {
        try {
            WebElement ele = getDriver().findElement(locator);
            ele.isDisplayed();
            return true;
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String getElementText(By locator) {
        return getDriver().findElement(locator).getText();
    }

    public static String getPageTitle() {
        return getDriver().getTitle();
    }

    public static void waitForVisibilityOfElement(By locator){
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Constants.EXPLICIT_WAIT_TIME));
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static void switchToNewTab(){
        Set<String> handles = getDriver().getWindowHandles();
        List<String> tabs = new ArrayList<String>(handles);
        getDriver().switchTo().window(tabs.get(1));
    }

    public static void switchToIframe(By locator){
        getDriver().switchTo().frame(getElement(locator));
    }

    public static void  closeNewOpenedTabAndWindow(){
        getDriver().close();
    }
    public static void takeScreenshot(String testMethodName) throws IOException, IOException {
        // Take screenshot and store as a file format
        File src = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
        // now copy the screenshot to desired location using copyFile //method
        FileUtils.copyFile(src,new File("D:\\maantic-workspace\\IntelliJ-workplace\\selenium-pom-framework\\screenshots\\" + testMethodName +".png"));
    }
    public static void selectDropDownByVisibleText(By locator, String text) {

        try {
            Select select = new Select(getElement(locator));
            select.selectByVisibleText(text);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void switchBackToDefaultContent(){
        getDriver().switchTo().defaultContent();
    }

    public static List<WebElement> getElementOfLists(By locator){
        return getDriver().findElements(locator);
    }

    public static void selectDropDownByIndex(By locator, int index) {
        try {
            Select select = new Select(getElement(locator));
            select.selectByIndex(index);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void moveMouseToElement(By locator){
        Actions actions = new Actions(getDriver());
        actions.moveToElement(getElement(locator)).perform();
    }

    public static String getAttributeValueOfElement(String attr,By locator) {
        try {
            return getElement(locator).getAttribute(attr);
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

}
