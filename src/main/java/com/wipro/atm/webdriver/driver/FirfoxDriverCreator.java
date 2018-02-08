package com.wipro.atm.webdriver.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class FirfoxDriverCreator extends WebDriverCreator {

	@Override
	public WebDriver factoryMethod() {		
		System.setProperty("webdriver.gecko.driver", ".\\src\\main\\resources\\drivers\\geckodriver.exe");
        WebDriver driver = new FirefoxDriver();
        return driver;	
	}
}
