package com.maantic.automation.pages;

import com.maantic.automation.base.BasePage;
import com.maantic.automation.utils.CommonUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class DevStudioPage extends BasePage {

    private By search_txtBox = By.xpath("(//input[contains(@name, 'SearchText')])[1]");
    private By search_icon_btn = By.xpath("//button[@title='Search'][i]");
    private By td_decision_table = By.xpath("//table[@id='bodyTbl_right']/tbody/tr/td/div/span/a[text()='Decision Table']/ancestor::tr[1]/td[1]/span");
    private By td_decision_table_name = By.xpath("//table[@id='bodyTbl_right']/tbody/tr/td/div/span/a[text()='Decision Table']/following::tr[1]/td[1]/nobr/span/a");

    //private By tbl_search_results = By.xpath("//table[@id='bodyTbl_right']/tbody/tr/td/span");
    //private By td_ruleName = By.xpath("(//table[@id='bodyTbl_right'])[2]/tbody/tr/td/div[contains(text(),'PegaFS:08-06-01')]");
    //private By lbl_decisionTableID = By.xpath("//span[@title='Purpose'][contains(text(),'RelatedPartyEnforcedPairs')]");

    public void enterSearchTermInSearchBox(String item) {    //enter search text in searchBox
        CommonUtils.waitForVisibilityOfElement(search_txtBox);
        CommonUtils.enterText(search_txtBox, item);
    }

    public void clickOnSearchIcon() {    //click on Search button
        CommonUtils.waitForVisibilityOfElement(search_icon_btn);
        CommonUtils.click(search_icon_btn);
    }

    //click on the search result after verifying ruleName, ruleType & ruleSetVersion
    public Boolean clickSearchResults(String ruleType, String ruleSetVersion, String ruleName) throws InterruptedException {
        Boolean temp = false;
        if (ruleType.equalsIgnoreCase("Decision_Table")) {
            if (CommonUtils.isElementPresent(td_decision_table_name)) {
                if (CommonUtils.getElementText(td_decision_table_name).equalsIgnoreCase(ruleName)) {
                    CommonUtils.waitForVisibilityOfElement(search_txtBox);
                    CommonUtils.click(td_decision_table);
                    Thread.sleep(3000);
                    getDriver().findElement(By.xpath("(//table[@id='bodyTbl_right'])[2]/tbody/tr/td/div[contains(text(),'" + ruleSetVersion + "')]")).click();
                    temp = true;
                }
            }
        } else if (ruleType.equalsIgnoreCase("Activity")) {

        } else if (ruleType.equalsIgnoreCase("SLA")) {

        }
        return temp;
    }
}
