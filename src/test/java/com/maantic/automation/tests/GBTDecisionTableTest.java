package com.maantic.automation.tests;

import com.maantic.automation.base.BasePage;
import com.maantic.automation.listeners.AllureListeners;
import com.maantic.automation.pages.*;
import com.maantic.automation.utils.ExcelDataProvider;
import com.maantic.automation.utils.ExcelUtils;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.Map;

@Listeners({ AllureListeners.class })
public class GBTDecisionTableTest extends BasePage {

    LoginPage loginPage = new LoginPage();
    DevStudioPage homePage = new DevStudioPage();
    DecisionTablePage dtPage = new DecisionTablePage();

    @Test(priority = 1, groups = {"Sanity","Regression"}, description = "Verifying the Decision Table is working properly.", dataProviderClass = ExcelDataProvider.class, dataProvider ="ExcelTestDataGBT" )
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Case Description: Verify the Decision Table is working properly.")
    public void Decision_Table(Map<String, String> map) throws InterruptedException {
        loginPage.enterUserNameText("Navneet");
        loginPage.enterPasswordText("rules");
        loginPage.clickOnLogInButton();
        Thread.sleep(5000);
        homePage.enterSearchTermInSearchBox(map.get("RuleName"));
        homePage.clickOnSearchIcon();
        Thread.sleep(3000);
        homePage.clickSearchResults(map.get("RuleType"), map.get("RuleSetVersion"));
        Assert.assertEquals(true, dtPage.isDecisionTableDisplayed(map.get("RuleName")));
        dtPage.clickActionsRunOfDecisionTable();
        //now switches to decision table window
        String dt_output = dtPage.switchWindowDecisionTable(map.get("Param1"));
        //writing 'actual' result in output file
        ExcelUtils.writeExcelData(dt_output, map.get("RuleType"), 17);  //'Actual' column=17
        Thread.sleep(5000);
        //writing 'pass/fail' in output file
        if(map.get("Expected").equalsIgnoreCase(dt_output)){
            ExcelUtils.writeExcelData("Pass", map.get("RuleType"), 18); //'Pass/Fail' column=18
            System.out.println("Pass");
        }
        else {
            ExcelUtils.writeExcelData("Fail", map.get("RuleType"), 18); //'Pass/Fail' column=18
            System.out.println("Fail");
        }
    }

}
