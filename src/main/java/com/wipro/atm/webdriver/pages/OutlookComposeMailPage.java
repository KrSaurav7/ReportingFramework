package com.wipro.atm.webdriver.pages;

import static com.wipro.atm.webdriver.utils.BaseUtility.getElementText;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wipro.atm.webdriver.utils.BaseUtility;
import com.wipro.atm.webdriver.utils.Constants;
import com.wipro.atm.webdriver.utils.WaitForElements;

public class OutlookComposeMailPage extends AbstractPage {
	Logger logger = LogManager.getRootLogger();

	@FindBy(xpath = "(//input[@autoid='_fp_5'])[1]")
	private WebElement txtSendTo;

	@FindBy(xpath = "//input[@autoid='_mcp_c']")
	private WebElement txtEmailSubject;

	@FindBy(xpath = "//div[@aria-label='Message body']")
	private WebElement txtEmailBody;

	@FindBy(xpath = "(//button[@autoid='_fce_1']/span)[1]")
	private WebElement btnNew;

	@FindBy(xpath = "(//button[@title='Send'])[2]")
	private WebElement btnSend;

	@FindBy(xpath = "(//span[@autoid='_pe_0'])[1]")
	private WebElement lblAutoPopulatedRecipientName;

	@FindBy(xpath = "(//span[contains(@class,'search ms-icon')])[2]")
	private WebElement btnSearchEmail;

	@FindBy(xpath = "//input[@autoid='_is_3']")
	private WebElement txtSearchEmail;

	@FindBy(xpath = "//em[contains(.,'subject for the mail')]")
	private WebElement lblAutoPopulatedSubject;

	@FindBy(xpath = "(//span[@autoid='_lvv_5'])[1]")
	private WebElement lblFirstSearchedEmail;

	@FindBy(xpath = "//div[@id='x_divtagdefaultwrapper']/p")
	private WebElement lblFirstSearchedBody;

	@FindBy(xpath = "//span[text()='Delete']")
	private WebElement lblFirstEmailDelete;

	@FindBy(xpath = "(//span[text()='Inbox'])[2]")
	private WebElement lblInbox;

	public OutlookComposeMailPage(WebDriver driver) {
		super(driver);
	}

	/**
	 * below method will compose a new email and send to the recipient
	 *
	 * @param email
	 *            - email of recipient
	 * @param subject
	 *            - subject of the email
	 * @param messagebody
	 *            - body of the email
	 * @return
	 */

	public OutlookComposeMailPage composeANewEmailAndSend(String email,
			String subject, String messagebody) {
		for (int i = 1; i <= Constants.NO_OF_EXEC; i++) {
			btnNew.click();
			WaitForElements.waitForElementClickable(driver, 30, btnSend);
			try {
				Thread.sleep(10000);
			} catch (InterruptedException exec) {
				logger.error(exec.getMessage());
			}
			txtSendTo.sendKeys(email);
			try {
				Thread.sleep(5000);
			} catch (InterruptedException exec) {
				logger.error(exec.getMessage());
			}
			WaitForElements.waitForElementClickable(driver, 30,
					lblAutoPopulatedRecipientName);
			lblAutoPopulatedRecipientName.click();
			WaitForElements
					.waitForElementClickable(driver, 20, txtEmailSubject);
			txtEmailSubject.sendKeys(subject);
			txtEmailSubject.sendKeys(Keys.TAB);
			WaitForElements.waitForElementClickable(driver, 20, txtEmailBody);
			txtEmailBody.sendKeys(messagebody);
			btnSend.click();
			System.out.println("Email has been composed and send");
			try {
				Thread.sleep(15000);
			} catch (InterruptedException e) {
				logger.error(e.getMessage());
			}
		}
		return new OutlookComposeMailPage(driver);
	}

	/**
	 * below method is to search email and delete the same
	 * 
	 * @param subject
	 *            - input email
	 * @return
	 */
	public OutlookComposeMailPage searchAndDeleteEmail(String subject,
			String msgBody) {
		WaitForElements.waitForElementClickable(driver, 30, btnSearchEmail);
		btnSearchEmail.click();
		WaitForElements.waitForElementClickable(driver, 30, txtSearchEmail);
		txtSearchEmail.sendKeys(subject);
		WaitForElements.waitForElementClickable(driver, 30,
				lblAutoPopulatedSubject);
		lblAutoPopulatedSubject.click();
		WaitForElements.waitForElementClickable(driver, 30,
				lblFirstSearchedEmail);
		for (int i = 1; i <= 3 * Constants.NO_OF_EXEC; i++) {
			WaitForElements.waitForElementClickable(driver, 30,
					lblFirstSearchedEmail);
			lblFirstSearchedEmail.click();
			WaitForElements.waitForElementToBeVisible(driver, 30,
					lblFirstSearchedBody);
			if (getElementText(lblFirstSearchedBody).equals(msgBody)) {
				WaitForElements.waitForElementClickable(driver, 30,
						lblFirstEmailDelete);
				lblFirstEmailDelete.click();
				logger.info("Deleting email which having same subject");
				try {
					Thread.sleep(10000);
				} catch (InterruptedException exc) {
					logger.error(exc.getMessage());
				}
			}
		}
		return new OutlookComposeMailPage(driver);
	}

	public boolean isElementDisplayed() {
		return BaseUtility.isElementDisplayed(lblFirstSearchedBody);
	}
}
