package com.wipro.atm.webdriver.pages;

import com.wipro.atm.webdriver.model.User;
import com.wipro.atm.webdriver.utils.BaseUtility;
import com.wipro.atm.webdriver.utils.WaitForElements;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class OpenCartSignInPage extends AbstractPage {
    Logger logger = LogManager.getRootLogger();

    @FindBy(xpath = "//a[text()='login']")
    private WebElement lnkLogin;

    @FindBy(xpath = "//input[@name='email']")
    private WebElement txtLoginEmail;

    @FindBy(xpath = "//input[@name='password']")
    private WebElement txtLoginPassword;

    @FindBy(xpath = "//input[@type='submit']")
    private WebElement btnLogin;

    @FindBy(xpath = "//h1[contains(text(),'My Account')]")
    private WebElement lblYourAccount;

    public OpenCartSignInPage(WebDriver driver) {

        super(driver);
    }

    /**
     * below method is to login to the open cart application
     * @param user - email and password
     * @return - driver
     */
    public OpenCartSignInPage loginToOpenCart(User user){
        WaitForElements.waitForElementClickable(driver, 10, lnkLogin);
        lnkLogin.click();
        WaitForElements.waitForElementClickable(driver, 10, txtLoginEmail);
        txtLoginEmail.sendKeys(user.getEmail());
        logger.info("Email " + user.getEmail() + " has been entered in email field");
        txtLoginPassword.sendKeys(user.getPassword());
        logger.info("Password has been entered");
        btnLogin.click();
        WaitForElements.waitForElementToBeVisible(driver, 30, lblYourAccount);
        return new OpenCartSignInPage(driver);
    }
    public boolean isElementDisplayed() {
        return BaseUtility.isElementDisplayed(lblYourAccount);
    }
}
