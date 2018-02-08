package com.wipro.atm.webdriver.pages;

import com.wipro.atm.webdriver.model.User;
import com.wipro.atm.webdriver.utils.BaseUtility;
import com.wipro.atm.webdriver.utils.WaitForElements;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class OutlookSignInPage extends AbstractPage {
    Logger logger = LogManager.getRootLogger();

    @FindBy(xpath = "//input[@id='userNameInput']")
    private WebElement txtLoginEmail;

    @FindBy(xpath = "//input[@id='passwordInput']")
    private WebElement txtLoginPassword;

    @FindBy(xpath = "//span[@id='submitButton']")
    private WebElement btnLogin;

    @FindBy(xpath = "//input[@value='Yes']")
    private WebElement btnStaySignIn;

    @FindBy(xpath = "(//button[@autoid='_fce_1']/span)[1]")
    private WebElement btnNewEmail;

    public OutlookSignInPage(WebDriver driver) {

        super(driver);
    }

    /**
     * below method is to login to the outlook application
     * @param user - email and password
     * @return - driver
     */
    public OutlookSignInPage loginToOutlook(User user){
        WaitForElements.waitForElementClickable(driver, 10, txtLoginEmail);
        txtLoginEmail.sendKeys(user.getEmail());
        logger.info("Email " + user.getEmail() + " has been entered in email field");
        txtLoginPassword.sendKeys(user.getPassword());
        logger.info("Password has been entered");
        btnLogin.click();
        WaitForElements.waitForElementClickable(driver, 30, btnStaySignIn);
        btnStaySignIn.click();
        return new OutlookSignInPage(driver);
    }
    public boolean isElementDisplayed() {
        return BaseUtility.isElementDisplayed(btnNewEmail);
    }
}

