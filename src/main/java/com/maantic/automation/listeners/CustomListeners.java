package com.maantic.automation.listeners;

import com.maantic.automation.base.BasePage;
import com.maantic.automation.utils.CommonUtils;
import org.testng.ITestListener;
import org.testng.ITestResult;
import java.io.IOException;

public class CustomListeners extends BasePage implements ITestListener {

    public void onTestStart(ITestResult result){
        System.out.println("Test is started...");
    }

    public void onTestSuccess(ITestResult result){
        System.out.println("Test is executed successfully...");
    }
    public void onTestFailure(ITestResult result){
        System.out.println("Test is failed...");
        try {
            CommonUtils.takeScreenshot(result.getMethod().getMethodName());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void onTestSkipped(ITestResult result){
        System.out.println("Test is skipped...");
    }
}
