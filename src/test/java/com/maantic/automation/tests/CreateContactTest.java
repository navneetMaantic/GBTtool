package com.maantic.automation.tests;

import com.maantic.automation.base.BasePage;
import com.maantic.automation.listeners.AllureListeners;
import com.maantic.automation.pages.ContactPage;
import com.maantic.automation.pages.DashboardPage;
import com.maantic.automation.pages.LoginPage;
import com.maantic.automation.utils.ExcelDataProvider;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.Map;
@Listeners({ AllureListeners.class })
public class CreateContactTest extends BasePage {

    ContactPage contactPage = new ContactPage();
    LoginPage loginPage = new LoginPage();
    DashboardPage dashboardPage = new DashboardPage();

   // @Test(priority = 1,groups = {"Sanity","Regression"},description = "Verifying IsNewContactPageDisplayed")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Case Description: Verify the new contact page displayed.")
    public void TC_01_VerifyIsNewContactPageDisplayed() throws InterruptedException {

        System.out.println("TC_01_VerifyIsNewContactPageDisplayed is called...");
        loginPage.enterUserNameText("Gopal");
        loginPage.enterPasswordText("rules");
        loginPage.clickOnLogInButton();
        dashboardPage.clickOnNewLink();
        contactPage.clickOnPhoneCallLink();
        Thread.sleep(2000);
        contactPage.enterAccountNumber("1234");
        Thread.sleep(2000);
        contactPage.clickOnSearchButton();
        Thread.sleep(2000);
        if(contactPage.isCustomerExist()) {
            contactPage.clickOnNewCreateContactButton();
        }
        Thread.sleep(2000);
        System.out.println(contactPage.isNewContactCreatePageDisplayed());
        Assert.assertTrue(false);
    }

    @Test(priority = 2,groups = {"Sanity","Regression"},description = "Verifying the contact is created successfully.",dataProviderClass = ExcelDataProvider.class,dataProvider ="ExcelTestData" )
    @Severity(SeverityLevel.BLOCKER)
    @Description("Test Case Description: Verify the new contact is created successfully.")
    public void TC_02_CreateNewContactSuccessfully(Map<String, String> map) throws InterruptedException {
        System.out.println("TC_02_CreateNewContactSuccessfully is called...");
        loginPage.enterUserNameText("Gopal");
        loginPage.enterPasswordText("rules");
        loginPage.clickOnLogInButton();
        Thread.sleep(5000);
        dashboardPage.clickOnNewLink();
        contactPage.clickOnPhoneCallLink();
        contactPage.enterAccountNumber("123");
        Thread.sleep(2000);
        contactPage.clickOnSearchButton();
        Thread.sleep(2000);
        if(contactPage.isCustomerExist()) {
            contactPage.clickOnNewCreateContactButton();
        }
        Thread.sleep(2000);
        contactPage.selectPrefixOption(map.get("Prefix"));
        contactPage.enterFirstName(map.get("FirstName"));
        contactPage.enterLastName(map.get("LastName"));
        contactPage.enterPrimaryEmail(map.get("Email"));
        Thread.sleep(5000);
        contactPage.clickOnSubmitButton();
        Thread.sleep(5000);
        System.out.println(contactPage.isNewContactCreatedSuccessfully(map.get("Email")));
        Assert.assertTrue(contactPage.isNewContactCreatedSuccessfully(map.get("Email")));
    }

}
