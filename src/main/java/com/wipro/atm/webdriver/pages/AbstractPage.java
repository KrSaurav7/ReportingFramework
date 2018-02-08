package com.wipro.atm.webdriver.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class AbstractPage {
    protected WebDriver driver;

    public AbstractPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public WebDriver getDriver() {

        return this.driver;
    }

    public boolean isElementPresent(By loacator) {

        return driver.findElements(loacator).size() > 0;
    }

}
