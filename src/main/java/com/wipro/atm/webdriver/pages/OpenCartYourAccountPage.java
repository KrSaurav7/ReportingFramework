package com.wipro.atm.webdriver.pages;

import com.wipro.atm.webdriver.utils.WaitForElements;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class OpenCartYourAccountPage extends AbstractPage {
    Logger logger = LogManager.getRootLogger();

    @FindBy(xpath = "//a[text()='Home']")
    private WebElement lnkHome;

    @FindBy(xpath = "//a[@class='nivo-imageLink']")
    private WebElement lnkCommingSoon;

    public OpenCartYourAccountPage(WebDriver driver) {
        super(driver);
    }

    /**
     * below method will navigate back to the home page
     */

    public OpenCartHomePage navigateToHomePage() {
        WaitForElements.waitForElementClickable(driver, 10, lnkHome);
        lnkHome.click();
        return new OpenCartHomePage(driver);
    }
    /**
     * below method will navigate to the comming soon product detail page
     */

    public OpenCartHomePage navigateToProductDetailPage(){
        WaitForElements.waitForElementClickable(driver, 10, lnkCommingSoon);
        lnkCommingSoon.click();
        return new OpenCartHomePage(driver);
    }

}
