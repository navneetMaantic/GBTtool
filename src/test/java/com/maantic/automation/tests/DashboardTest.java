package com.maantic.automation.tests;

import com.maantic.automation.base.BasePage;
import com.maantic.automation.listeners.AllureListeners;
import com.maantic.automation.pages.DashboardPage;
import com.maantic.automation.pages.LoginPage;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({ AllureListeners.class })
public class DashboardTest extends BasePage {

    LoginPage loginPage = new LoginPage();
    DashboardPage dashboardPage = new DashboardPage();

    @Test(priority = 1 ,groups = {"Sanity"},description = "Verifying Dashboard Page Title")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Case Description: Verify the title on Dashboard Page")
    public void TC_01_VerifyDashboardPageTitle() throws InterruptedException {
        System.out.println("TC_01_VerifyDashboardPageTitle is called...");

        loginPage.enterUserNameText("Gopal");
        loginPage.enterPasswordText("rules");
        loginPage.clickOnLogInButton();
        Thread.sleep(5000);
        String actualPageTitle = dashboardPage.getDashboardPageTitle();
        Assert.assertEquals(actualPageTitle, "Interaction Portal");
    }


    @Test(priority = 2,groups = {"Regression"},description = "Verifying LoggedInUserName")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Case Description: Verify the LoggedInUserName")
    public void TC_02_VerifyLoggedInUserName() {
        System.out.println("TC_02_VerifyLoggedInUserName is called...");

        loginPage.enterUserNameText("Gopal");
        loginPage.enterPasswordText("rules");
        loginPage.clickOnLogInButton();
        String actualLoggedInUser = dashboardPage.getLoggedInUserName("title");
        System.out.println(actualLoggedInUser);
        Assert.assertEquals(actualLoggedInUser,"Gopal");
    }
}
