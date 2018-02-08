package com.wipro.atm.webdriver.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class OpenCartStartPage extends AbstractPage {
    Logger logger = LogManager.getRootLogger();

    public OpenCartStartPage(WebDriver driver) {
        super(driver);
    }

    /**
     * below method will start the browser based on the browser preferance
     */

    public OpenCartHomePage openOpenCartPage(WebDriver driver, String openCartURL) {
        driver.get(openCartURL);
        logger.info("Successfully Launched OpenCart Application");
        return new OpenCartHomePage(driver);
    }
}
