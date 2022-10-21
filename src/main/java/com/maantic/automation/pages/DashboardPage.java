package com.maantic.automation.pages;

import com.maantic.automation.utils.CommonUtils;
import org.openqa.selenium.By;

public class DashboardPage {

    private By search_txtBox = By.xpath("//input[@title='Search for an item']");
    private By dashboard_txt = By.xpath("//span[@class='menu-item-title'][@xpath='1']");
    private By new_link = By.xpath("//a[@name='NewInteraction_pyDisplayHarness_1'][@title='New']");
    private By loggedIn_user_txt= By.xpath("//button[@type='button'][@title='Gopal']");
    private By iframe01_name = By.xpath("//iframe[@name='PegaGadget0Ifr']");
    private By Search_icon_btn = By.xpath("//i[@class='pi pi-search-2 pi-regular']");

    private By table_grid_row= By.xpath("//*[@id='bodyTbl_right']//tr");

    public String getDashboardPageTitle() {
        return CommonUtils.getPageTitle();
    }

    public int getGridResultCount(){
        return CommonUtils.getElementOfLists(table_grid_row).size();
    }

    public String getDashboardPageContent(){
        CommonUtils.switchToIframe(iframe01_name);
        CommonUtils.waitForVisibilityOfElement(dashboard_txt);
        return CommonUtils.getElementText(dashboard_txt);
    }

    public boolean isDashboardPageDisplayed(){
        CommonUtils.switchToIframe(iframe01_name);
        CommonUtils.waitForVisibilityOfElement(dashboard_txt);
       return CommonUtils.isElementPresent(dashboard_txt);
    }

    public void clickOnNewLink(){
        CommonUtils.waitForVisibilityOfElement(new_link);
        CommonUtils.click(new_link);
    }

    public String getLoggedInUserName(String attr){
        CommonUtils.waitForVisibilityOfElement(loggedIn_user_txt);
        return CommonUtils.getAttributeValueOfElement(attr,loggedIn_user_txt);
    }

    public void enterSearchTermInSearchBox(String item){
        CommonUtils.waitForVisibilityOfElement(search_txtBox);
        CommonUtils.enterText(search_txtBox,item);
    }

    public void clickOnSearchIcon(){
        CommonUtils.waitForVisibilityOfElement(Search_icon_btn);
        CommonUtils.click(Search_icon_btn);
    }
}
