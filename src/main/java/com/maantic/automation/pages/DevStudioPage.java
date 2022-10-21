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

    //private By tbl_search_results = By.xpath("//table[@id='bodyTbl_right']/tbody/tr/td/span");
    //private By td_ruleName = By.xpath("(//table[@id='bodyTbl_right'])[2]/tbody/tr/td/div[contains(text(),'PegaFS:08-06-01')]");
    //private By lbl_decisionTableID = By.xpath("//span[@title='Purpose'][contains(text(),'RelatedPartyEnforcedPairs')]");

    public void enterSearchTermInSearchBox(String item){    //enter search text in searchBox
        CommonUtils.waitForVisibilityOfElement(search_txtBox);
        CommonUtils.enterText(search_txtBox,item);
    }

    public void clickOnSearchIcon(){    //click on Search button
        CommonUtils.waitForVisibilityOfElement(search_icon_btn);
        CommonUtils.click(search_icon_btn);
    }

    public void clickSearchResults(String ruleType, String ruleSetVersion){ //click on the search result after verifying ruleType & rulesetVersion
        if(ruleType.equalsIgnoreCase("Decision_Table")) {
            CommonUtils.waitForVisibilityOfElement(search_txtBox);
            CommonUtils.click(td_decision_table);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            getDriver().findElement(By.xpath("(//table[@id='bodyTbl_right'])[2]/tbody/tr/td/div[contains(text(),'"+ruleSetVersion+"')]")).click();
        }
    }

    //    public String getDashboardPageContent(){
//        CommonUtils.switchToIframe(iframe01_name);
//        CommonUtils.waitForVisibilityOfElement(dashboard_txt);
//        return CommonUtils.getElementText(dashboard_txt);
//    }

//    public String getLoggedInUserName(String attr){
//        CommonUtils.waitForVisibilityOfElement(loggedIn_user_txt);
//        return CommonUtils.getAttributeValueOfElement(attr,loggedIn_user_txt);
//    }
}
