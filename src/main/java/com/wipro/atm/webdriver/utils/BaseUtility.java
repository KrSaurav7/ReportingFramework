package com.wipro.atm.webdriver.utils;

import org.openqa.selenium.WebElement;

public class BaseUtility {

	public static boolean isElementDisplayed(WebElement element) {
		return element.isDisplayed();
	}

	public static String getElementText(WebElement element) {
		return element.getText();
	}

	public static String getAttributeValue(WebElement element, String attribute) {
		return element.getAttribute(attribute);
	}
}
