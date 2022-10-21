package com.maantic.automation.tests;

import com.maantic.automation.base.BasePage;
import com.maantic.automation.listeners.AllureListeners;
import com.maantic.automation.pages.ContactPage;
import com.maantic.automation.pages.DashboardPage;
import com.maantic.automation.pages.LoginPage;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
@Listeners({ AllureListeners.class })
public class SearchContactTest extends BasePage {
    ContactPage contactPage = new ContactPage();
    LoginPage loginPage = new LoginPage();
    DashboardPage dashboardPage = new DashboardPage();

   //@Test(priority = 1,description = "Verifying Searching of new contact.")
   @Severity(SeverityLevel.NORMAL)
   @Description("Test Case Description: Verify the contact is searched successfully.")
    public void TC_01_VerifySearchingNewlyCreatedContact() throws InterruptedException {
        System.out.println("TC_01_VerifySearchingNewlyCreatedContact is called...");

       loginPage.enterUserNameText("Gopal");
       loginPage.enterPasswordText("rules");
        loginPage.clickOnLogInButton();
        Thread.sleep(3000);
        dashboardPage.enterSearchTermInSearchBox("Gopal_Test");
        dashboardPage.clickOnSearchIcon();
        Thread.sleep(8000);
        contactPage.switchTo1stIframe();
        Thread.sleep(8000);
       System.out.println("Result Count: "+dashboardPage.getGridResultCount());
        if(dashboardPage.getGridResultCount()>0){
            Assert.assertTrue(true);
        }
        else{
            Assert.assertTrue(false);
        }

    }
}
