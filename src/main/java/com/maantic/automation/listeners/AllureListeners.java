package com.maantic.automation.listeners;

import com.maantic.automation.base.BasePage;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class AllureListeners implements ITestListener {


    private static String getTestMethodName(ITestResult iTestResult) {
        return iTestResult.getMethod().getConstructorOrMethod().getName();
    }

    // Text attachments for Allure
    @Attachment(type = "image/png")
    public byte[] saveScreenshotOnFailure(WebDriver driver) {
        byte[] screenShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        return screenShot;
    }

    // Text attachments for Allure
    @Attachment(value = "{0}", type = "text/plain")
    public static String saveTextLogs(String message) {
        return message;
    }

    // HTML attachments for Allure
    @Attachment(value = "{0}", type = "text/html")
    public static String attachHtml(String html) {
        return html;
    }


    @Override
    public void onStart(ITestContext iTestContext) {
        System.out.println("Script in onStart method " + iTestContext.getName());
        iTestContext.setAttribute("WebDriver", BasePage.getDriver());
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        System.out.println("Script in onFinish method " + iTestContext.getName());
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
        System.out.println("Script in onTestStart method " + getTestMethodName(iTestResult) + " start");
    }

    public void onTestSuccess(ITestResult iTestResult) {
        System.out.println("Script in onTestSuccess method " + getTestMethodName(iTestResult) + " succeed");
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        System.out.println("Script in onTestFailure method " + getTestMethodName(iTestResult) + " failed");
        // Allure ScreenShotRobot and SaveTestLog
        if (BasePage.getDriver() != null) {
          //  System.out.println("Screenshot captured for test case:" + getTestMethodName(iTestResult));
            saveScreenshotOnFailure(BasePage.getDriver());
        }
        // Save a log on allure.
        saveTextLogs(getTestMethodName(iTestResult) + " failed and screenshot taken!");
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        System.out.println("Script in onTestSkipped method " + getTestMethodName(iTestResult) + " skipped");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        System.out.println("Test failed but it is in defined success ratio " + getTestMethodName(iTestResult));
    }
}
