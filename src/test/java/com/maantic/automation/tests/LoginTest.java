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
public class LoginTest extends BasePage {

    LoginPage loginPage = new LoginPage();
    DashboardPage dashboardPage = new DashboardPage();

    //@Test(priority = 1,groups = {"Sanity","Regression"},description = "Verifying Login Page Title")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Case Description: Verify the title on Login Page")
    public void TC_01_VerifyLoginPageTitle(){
        System.out.println("verifyLoginPageTitle is called...");

        if(loginPage.isLoginPageDisplayed()) {
            String actualPageTitle = loginPage.getLoginPageTitle();
            Assert.assertEquals(actualPageTitle, "Login Page");
            Assert.assertTrue(true);
        }
        else{
            Assert.assertTrue(false);
        }
    }

    @Test(priority = 2,groups = {"Sanity","Regression"},description = "Verifying Valid Login")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Case Description: Verify the valid login.")
    public void TC_02_VerifyLoggedInSuccessfully() throws InterruptedException {
        System.out.println("verifyLoggedInSuccessfully is called...");

        loginPage.enterUserNameText("Navneet");
        loginPage.enterPasswordText("rules");
        loginPage.clickOnLogInButton();
        Thread.sleep(5000);
        String actualDashboardTitle= dashboardPage.getDashboardPageTitle();
        System.out.println("Dashboard Title: "+dashboardPage.getDashboardPageTitle());
        Assert.assertEquals(actualDashboardTitle,"Pega Dev Studio");
    }
}
