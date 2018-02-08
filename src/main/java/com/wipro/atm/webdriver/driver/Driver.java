package com.wipro.atm.webdriver.driver;

import org.openqa.selenium.WebDriver;

public class Driver {

	private static WebDriver driver;
	
	private Driver() {
	}

	public static WebDriver intializeDriver(String browser) {
		if (null == driver) {
			if ("chrome".equalsIgnoreCase(browser)) {
				WebDriverCreator creator = new ChromeDriverCreator();
				driver = creator.factoryMethod();				
			} else if ("firefox".equalsIgnoreCase(browser)) {
				WebDriverCreator creator = new FirfoxDriverCreator();
				driver = creator.factoryMethod();				
			}
		}
		return driver;
	}

	public static void closeWebBrowser() {
		if (null != driver) {
			driver.quit();
		}
		if (null != driver) {
			driver = null;
		}
	}
}
