package com.wipro.atm.webdriver.utils;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitForElements {
	public static void waitForElementClickable(WebDriver driver, int timeout,
			final WebElement element) {
		new WebDriverWait(driver, timeout).pollingEvery(2, TimeUnit.SECONDS)
				.until(ExpectedConditions.elementToBeClickable(element));
	}

	public static void waitForElementToBeVisible(WebDriver driver, int timeout,
			final WebElement element) {
		new WebDriverWait(driver, timeout).pollingEvery(2, TimeUnit.SECONDS)
				.until(ExpectedConditions.visibilityOf(element));
	}

}
