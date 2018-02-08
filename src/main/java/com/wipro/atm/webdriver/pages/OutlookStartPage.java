package com.wipro.atm.webdriver.pages;

import com.wipro.atm.webdriver.utils.WaitForElements;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class OutlookStartPage extends AbstractPage {
    Logger logger = LogManager.getRootLogger();

    @FindBy(xpath = "//span[text()='Sign out']")
    private WebElement lblOutlooksignOut;

    @FindBy(xpath = "(//span[contains(text(),'BAS')])[1]")
    private WebElement lblAccountName;

    public OutlookStartPage(WebDriver driver) {
        super(driver);
    }

    /**
     * below method will start the browser based on the browser preferance
     */

    public OutlookStartPage openOutlookApp(WebDriver driver, String outlookURL) {
        driver.get(outlookURL);
        logger.info("Successfully Launched outlook Application");
        return new OutlookStartPage(driver);
    }

    /**
     * below method will log out from the application
     */

    public OutlookStartPage outlookLogout() {
        WaitForElements.waitForElementClickable(driver, 10, lblAccountName);
        lblAccountName.click();
        WaitForElements.waitForElementClickable(driver, 10, lblOutlooksignOut);
        lblOutlooksignOut.click();
        return new OutlookStartPage(driver);
    }
}
