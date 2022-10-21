package com.maantic.automation.pages;

import com.maantic.automation.utils.CommonUtils;
import org.openqa.selenium.By;

public class LoginPage {
    private By userName_txtBox = By.id("txtUserID");
    private By password_txtBox = By.id("txtPassword");
    private By login_btn = By.xpath("//span[@class='loginButtonText']");

    public String getLoginPageTitle() {
        return CommonUtils.getPageTitle();
    }

    public boolean isLoginPageDisplayed() {
        CommonUtils.waitForVisibilityOfElement(login_btn);
        return CommonUtils.isElementPresent(login_btn);
    }

    public void enterUserNameText(String user) {
        CommonUtils.waitForVisibilityOfElement(userName_txtBox);
        CommonUtils.enterText(userName_txtBox, user);
    }

    public void enterPasswordText(String pass) {
        CommonUtils.waitForVisibilityOfElement(password_txtBox);
        CommonUtils.enterText(password_txtBox, pass);
    }

    public void clickOnLogInButton() {
        CommonUtils.waitForVisibilityOfElement(login_btn);
        CommonUtils.click(login_btn);
    }

}
