package com.wipro.atm.webdriver.pages;

import static com.wipro.atm.webdriver.utils.BaseUtility.getElementText;
import static com.wipro.atm.webdriver.utils.Constants.getRandomEmail;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import com.wipro.atm.webdriver.model.User;
import com.wipro.atm.webdriver.utils.BaseUtility;
import com.wipro.atm.webdriver.utils.WaitForElements;

public class RegisterAccountPage extends AbstractPage {
	Logger logger = LogManager.getRootLogger();
	private static String userCreatedMessage;

	@FindBy(xpath = "//input[@name='firstname']")
	private WebElement txtFirstName;

	@FindBy(xpath = "//input[@name='lastname']")
	private WebElement txtLastName;

	@FindBy(xpath = "//input[@name='email']")
	private WebElement txtEmail;

	@FindBy(xpath = "//input[@name='telephone']")
	private WebElement txtTelephone;

	@FindBy(xpath = "//input[@name='address_1']")
	private WebElement txtAddress;

	@FindBy(xpath = "//input[@name='city']")
	private WebElement txtCity;

	@FindBy(xpath = "//input[@name='postcode']")
	private WebElement txtPostcode;

	@FindBy(xpath = "//select[@name='country_id']")
	private WebElement cmbCountry;

	@FindBy(xpath = "//select[@name='zone_id']")
	private WebElement cmbState;

	@FindBy(xpath = "//input[@name='password']")
	private WebElement txtPassword;

	@FindBy(xpath = "//input[@name='confirm']")
	private WebElement txtConfirmPassword;

	@FindBy(xpath = "//input[@type='submit']")
	private WebElement btnContinue;

	@FindBy(xpath = "//input[@name='agree']")
	private WebElement chkAgree;

	@FindBy(xpath = "//div[@id='content']//h1")
	private WebElement lblAccountCreatedMsg;

	public RegisterAccountPage(WebDriver driver) {
		super(driver);
	}

	/**
	 * below method will provide all the necessary details which is required to
	 * craete an account
	 */

	public OpenCartYourAccountPage fillRegistrationFormAndSubmit(User user,
			String accountCreatedMsg) {
		WaitForElements.waitForElementClickable(driver, 10, btnContinue);
		txtFirstName.sendKeys(user.getFirstName());
		txtLastName.sendKeys(user.getLastName());
		txtEmail.sendKeys(getRandomEmail(user.getFirstName(),
				user.getLastName()));
		logger.info("Email for new user : "
				+ getRandomEmail(user.getFirstName(), user.getLastName()));
		txtTelephone.sendKeys(user.getTelephone());
		txtAddress.sendKeys(user.getAddress());
		txtCity.sendKeys(user.getCity());
		txtPostcode.sendKeys(user.getPostCode());
		Select selectCountry = new Select(cmbCountry);
		selectCountry.selectByVisibleText(user.getCountry());
		Select selectState = new Select(cmbState);
		selectState.selectByVisibleText(user.getState());
		txtPassword.sendKeys(user.getPassword());
		txtConfirmPassword.sendKeys(user.getConfirmPassword());
		chkAgree.click();
		btnContinue.click();
		WaitForElements.waitForElementToBeVisible(driver, 30,
				lblAccountCreatedMsg);
		userCreatedMessage = getElementText(lblAccountCreatedMsg);
		try {
			if (userCreatedMessage.equals(accountCreatedMsg)) {
				logger.info("User Has been created successfully");
			} else {
				throw new UserNotCreatedException(
						"User has not been created Please provide all mandatory details");
			}
		} catch (UserNotCreatedException userNotCreatedExec) {
			logger.error("Errror occured :" + userNotCreatedExec.getMessage());
		}
		return new OpenCartYourAccountPage(driver);
	}

	public boolean isElementDisplayed() {

		return BaseUtility.isElementDisplayed(lblAccountCreatedMsg);
	}
}
