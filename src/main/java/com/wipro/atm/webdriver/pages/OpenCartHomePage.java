package com.wipro.atm.webdriver.pages;


import com.wipro.atm.webdriver.utils.WaitForElements;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import static com.wipro.atm.webdriver.utils.BaseUtility.getElementText;

public class OpenCartHomePage extends AbstractPage {
    Logger logger = LogManager.getRootLogger();
    public String firstFeatureProdName;


    @FindBy(xpath = "//a[contains(text(),'create an account')]")
    private WebElement lnkCreateAnAccount;

    @FindBy(xpath = "//a[text()='Logout']")
    private WebElement signOut;

    @FindBy(xpath = "//div[@class='box-product']/div[1]//img")
    private WebElement imgFirstFeatureProduct;

    @FindBy(xpath = "//div[@class='box-product']/div[1]/div[@class='name']/a")
    private WebElement imgFirstFeatureProductName;

    @FindBy(xpath = "//div[@id='content']/h1")
    private WebElement lblSelectedProductName;

    public OpenCartHomePage(WebDriver driver) {
        super(driver);
    }

    /**
     * below method will proceed for new registration by clicking on crate new account btn
     */

    public OpenCartHomePage openCartCreateAnAccount() {
        WaitForElements.waitForElementClickable(driver, 10, lnkCreateAnAccount);
        lnkCreateAnAccount.click();
        logger.info("Clicked on lnkCreateAnAccount in order to create new account ");
        return new OpenCartHomePage(driver);
    }

    /**
     * below method will select first feature product and verify the same in PDP page
     */

    public OpenCartHomePage selectFirstFeatureProductAndVerify(){
        WaitForElements.waitForElementClickable(driver, 10, imgFirstFeatureProduct);
        firstFeatureProdName = getElementText(imgFirstFeatureProductName);
        imgFirstFeatureProduct.click();
        WaitForElements.waitForElementToBeVisible(driver,30,lblSelectedProductName);
        Assert.assertEquals(firstFeatureProdName, getElementText(lblSelectedProductName));
        logger.info("Selected product from home page displayed successfully");
        return new OpenCartHomePage(driver);
    }

    /**
     * below method will log out from the application
     */

    public OpenCartHomePage openCartLogout() {
        WaitForElements.waitForElementClickable(driver, 10, signOut);
        signOut.click();
        return new OpenCartHomePage(driver);
    }

}
