package com.wipro.atm.webdriver.pages;

import static com.wipro.atm.webdriver.utils.BaseUtility.getElementText;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import com.wipro.atm.webdriver.utils.WaitForElements;

public class OpenCartCheckOutPage extends AbstractPage {
	public String lblCheckoutProductName;

	@FindBy(xpath = "//div[@id='content']//a[text()='Checkout']")
	private WebElement lnkCheckout;

	@FindBy(xpath = "//input[@value='Continue']")
	private WebElement btnCheckoutContinue;

	@FindBy(xpath = "//input[@name='agree']")
	private WebElement chkIAgree;

	@FindBy(xpath = "//h1[text()='Your Order Has Been Processed!']")
	private WebElement lblOrderConfirmationMsg;

	@FindBy(xpath = "//input[@id='button-payment-method']")
	private WebElement btnAgreeContinue;

	@FindBy(xpath = "//input[@id='button-confirm']")
	private WebElement btnConfirmOrder;

	@FindBy(xpath = "//div[@class='checkout-product']//a")
	private WebElement lnkCheckoutProductName;

	@FindBy(xpath = "//div[@id='footer']//a[text()='My Account']")
	private WebElement lnkYourAccount;

	@FindBy(xpath = "//a[text()='View your order history']")
	private WebElement lnkViewYourOrderHistory;

	@FindBy(xpath = "(//div[@class='order-info']/a/img)[1]")
	private WebElement imgViewOrder;

	@FindBy(xpath = "//div[@id='content']/table[3]/tbody/tr/td[1]")
	private WebElement lblOrderHistoryProductName;

	public OpenCartCheckOutPage(WebDriver driver) {
		super(driver);
	}

	/**
	 * below method will proceed for checkout
	 * 
	 * @return - driver
	 */

	public OpenCartCheckOutPage proceedForCheckout() {
		WaitForElements.waitForElementClickable(driver, 30, lnkCheckout);
		lnkCheckout.click();
		WaitForElements
				.waitForElementClickable(driver, 10, btnCheckoutContinue);
		btnCheckoutContinue.click();
		WaitForElements.waitForElementClickable(driver, 10, chkIAgree);
		chkIAgree.click();
		btnAgreeContinue.click();
		WaitForElements.waitForElementToBeVisible(driver, 10, btnConfirmOrder);
		return new OpenCartCheckOutPage(driver);
	}

	/**
	 * below method will place an order and verify the success message
	 * 
	 * @return - driver
	 */
	public OpenCartCheckOutPage confirmOrderAndVerify(
			String orderConfirmationMsg) {
		WaitForElements.waitForElementClickable(driver, 10, btnConfirmOrder);
		lblCheckoutProductName = getElementText(lnkCheckoutProductName);
		btnConfirmOrder.click();
		WaitForElements.waitForElementToBeVisible(driver, 10,
				lblOrderConfirmationMsg);
		Assert.assertEquals(getElementText(lblOrderConfirmationMsg),
				orderConfirmationMsg);
		return new OpenCartCheckOutPage(driver);
	}

	/**
	 * below method is to verify whether selected product is being placed or not
	 */

	public OpenCartCheckOutPage verifyOrderInOrderHistory() {
		WaitForElements.waitForElementClickable(driver, 10, lnkYourAccount);
		lnkYourAccount.click();
		WaitForElements.waitForElementClickable(driver, 10,
				lnkViewYourOrderHistory);
		lnkViewYourOrderHistory.click();
		WaitForElements.waitForElementClickable(driver, 10, imgViewOrder);
		imgViewOrder.click();
		WaitForElements.waitForElementToBeVisible(driver, 10,
				lblOrderHistoryProductName);
		Assert.assertEquals(lblCheckoutProductName,
				getElementText(lblOrderHistoryProductName));
		return new OpenCartCheckOutPage(driver);
	}

}
