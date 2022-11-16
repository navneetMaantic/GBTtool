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
//    private By td_decision_table_name = By.xpath("//table[@id='bodyTbl_right']/tbody/tr/td/div/span/a[text()='Decision Table']/following::tr[1]/td[1]/nobr/span/a");

    private By getTdDecisionTableName(String className, String ruleName){
        return By.xpath("//table[@id='bodyTbl_right']/tbody/tr/td/div/span[text()='"+ className +"']/preceding::tr[1]/td/nobr/span/a[text()='"+ ruleName +"']");
    }
    private By getTdSLAName(String className, String ruleName){
        return By.xpath("//table[@id='bodyTbl_right']/tbody/tr/td/div/span[text()='"+ className +"']/preceding::tr[1]/td/nobr/span/a[text()='"+ ruleName +"']");
    }
    private By getTdExpand(String className){
        return By.xpath("//table[@id='bodyTbl_right']/tbody/tr/td/div/span[text()='"+ className +"']/preceding::td[@class='expandPane    rowHandle evenRow']");
    }
    private By getTdRuleSetVersion(String ruleSetVersion){
        return By.xpath("(//table[@id='bodyTbl_right'])[2]/tbody/tr/td/div[contains(text(),'" + ruleSetVersion + "')]");
    }
    public void temp(String ruleName, String className){
        List<WebElement> rowList = getDriver().findElements(By.xpath("(//table[@id='bodyTbl_right'])[1]/tbody/tr/td[3]/div"));
        List<WebElement> rowList2 = getDriver().findElements(By.xpath("(//table[@id='bodyTbl_right'])[1]/tbody/tr/td[4]/div"));
        int i;
        for (i=0; i<rowList.size();i++){
            String lblruleName = rowList.get(i).getText();
            String lblclassName = rowList2.get(i).getText();
            if(lblruleName.equalsIgnoreCase(ruleName) && lblclassName.equalsIgnoreCase(className)){
                System.out.println("temp=" + lblruleName);
                getDriver().findElement(By.xpath("//a[text()='"+ruleName+"']/preceding::td[@class='expandPane    rowHandle evenRow'][1]")).click();
                break;
            }
        }
    }

    public void enterSearchTermInSearchBox(String item) {    //enter search text in searchBox
        CommonUtils.waitForVisibilityOfElement(search_txtBox);
        CommonUtils.enterText(search_txtBox, item);
    }

    public void clickOnSearchIcon() {    //click on Search button
        CommonUtils.waitForVisibilityOfElement(search_icon_btn);
        CommonUtils.click(search_icon_btn);
    }

    //click on the search result after verifying ruleName, ruleType & ruleSetVersion
    public Boolean clickSearchResults(String ruleType, String className, String ruleSetVersion, String ruleName) throws InterruptedException {
        Boolean temp = false;
        if (ruleType.equalsIgnoreCase("Decision_Table")) {
            if (CommonUtils.isElementPresent(getTdDecisionTableName(className, ruleName))) {
                if (CommonUtils.getElementText(getTdDecisionTableName(className, ruleName)).equalsIgnoreCase(ruleName)) {
                    CommonUtils.waitForVisibilityOfElement(search_txtBox);
                    temp(ruleName, className);
                    CommonUtils.click(getTdExpand(className));
                    Thread.sleep(3000);
                    CommonUtils.click(getTdRuleSetVersion(ruleSetVersion));
//                    getDriver().findElement(By.xpath("(//table[@id='bodyTbl_right'])[2]/tbody/tr/td/div[contains(text(),'" + ruleSetVersion + "')]")).click();
                    temp = true;
                }
            }
        } else if (ruleType.equalsIgnoreCase("Activity")) {

        } else if (ruleType.equalsIgnoreCase("SLA")) {
            if (CommonUtils.isElementPresent(getTdSLAName(className, ruleName))) {
                if (CommonUtils.getElementText(getTdSLAName(className, ruleName)).equalsIgnoreCase(ruleName)) {
                    CommonUtils.waitForVisibilityOfElement(search_txtBox);
                    temp(ruleName, className);
                    CommonUtils.click(getTdExpand(className));
                    Thread.sleep(3000);
                    CommonUtils.click(getTdRuleSetVersion(ruleSetVersion));
                    temp = true;
                }
            }
        }
        return temp;
    }
}
