package com.maantic.automation.pages;

import com.maantic.automation.utils.CommonUtils;
import org.openqa.selenium.By;

public class ContactPage {

    private By phone_call_link = By.xpath("//span[contains(text(),'Phone call')]");
    private By iframe01_name = By.cssSelector("iframe[name='PegaGadget1Ifr']");
    private By iframe02_name = By.cssSelector("iframe[name='PegaGadget2Ifr']");

    private By accountNumber_txt = By.xpath("//input[@title='Account number']");
    private By search_btn = By.xpath("//button[@title='Search'][@type='button']");
    private By create_contact_btn = By.xpath("//button[@title='Create new contact'][@type='button']");

    private By customer_non_exist_msg= By.xpath("//div[contains(text(),'This customer does not exist')]");
    private By firstName_txt= By.xpath("//input[@title='First Name']");
    private By lastName_txt= By.xpath("//input[@title='Last Name']");
    private By primary_email_txt= By.xpath("//input[@title='Primary email']");
    private By submit_btn= By.xpath("//button[@title='Complete this assignment']");
    private By prefix_select_option= By.xpath("//select[@title='Select Prefix']");
    private By search_by_option = By.xpath("//select[@title='Search by']");
    private By email_content = By.xpath("//span[contains(text(),'Email')]/following::div//span");


    public void selectPrefixOption(String option){

        CommonUtils.waitForVisibilityOfElement(prefix_select_option);
        CommonUtils.selectDropDownByVisibleText(prefix_select_option,option);
    }

    public boolean isNewContactCreatePageDisplayed(){
        //CommonUtils.switchToIframe(iframe01_name);
        CommonUtils.waitForVisibilityOfElement(submit_btn);
        return CommonUtils.isElementPresent(submit_btn);
    }

    public boolean isNewContactCreatedSuccessfully(String email){
        CommonUtils.waitForVisibilityOfElement(email_content);
        if(CommonUtils.isElementPresent(email_content)){
            if(CommonUtils.getElementText(email_content).equalsIgnoreCase(email)){
                return true;
            }
        }
        return false;

    }

    public void selectSearchByOption(String option){
        CommonUtils.switchToIframe(iframe01_name);
        CommonUtils.waitForVisibilityOfElement(search_by_option);
        CommonUtils.selectDropDownByVisibleText(search_by_option,option);
        CommonUtils.switchBackToDefaultContent();
    }

    public void switchTo2ndIframe(){
        CommonUtils.switchToIframe(iframe02_name);
    }
    public void switchTo1stIframe(){
        CommonUtils.switchToIframe(iframe01_name);
    }

    public void enterFirstName(String fName){
        CommonUtils.waitForVisibilityOfElement(firstName_txt);
        CommonUtils.enterText(firstName_txt,fName);
    }

    public void enterLastName(String lName){
        CommonUtils.waitForVisibilityOfElement(lastName_txt);
        CommonUtils.enterText(lastName_txt,lName);
    }

    public void enterPrimaryEmail(String pEmail){
        CommonUtils.waitForVisibilityOfElement(primary_email_txt);
        CommonUtils.enterText(primary_email_txt,pEmail);
    }
    public void clickOnSubmitButton(){
        CommonUtils.waitForVisibilityOfElement(submit_btn);
        CommonUtils.click(submit_btn);
    }
    public void clickOnPhoneCallLink() throws InterruptedException {
        Thread.sleep(2000);
        CommonUtils.moveMouseToElement(phone_call_link);
        CommonUtils.waitForVisibilityOfElement(phone_call_link);
        CommonUtils.click(phone_call_link);
        Thread.sleep(3000);
    }

    public void enterAccountNumber(String number) throws InterruptedException {
        Thread.sleep(1000);
        CommonUtils.switchToIframe(iframe01_name);
        CommonUtils.waitForVisibilityOfElement(accountNumber_txt);
        CommonUtils.enterText(accountNumber_txt,number);

    }

    public void clickOnSearchButton() throws InterruptedException {
        Thread.sleep(2000);
        CommonUtils.scrollDownPage(300);
        CommonUtils.waitForVisibilityOfElement(search_btn);
        CommonUtils.click(search_btn);
    }

    public void clickOnNewCreateContactButton(){
        CommonUtils.waitForVisibilityOfElement(create_contact_btn);
        CommonUtils.click(create_contact_btn);
    }

    public boolean isCustomerExist(){
        CommonUtils.waitForVisibilityOfElement(customer_non_exist_msg);
        return CommonUtils.isElementPresent(customer_non_exist_msg);
    }


}
