package com.wipro.atm.webdriver.tests;

import java.util.LinkedHashMap;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;
import com.wipro.atm.webdriver.driver.Driver;
import com.wipro.atm.webdriver.model.User;
import com.wipro.atm.webdriver.pages.OpenCartHomePage;
import com.wipro.atm.webdriver.pages.OpenCartProductDetailPage;
import com.wipro.atm.webdriver.pages.OpenCartStartPage;
import com.wipro.atm.webdriver.pages.OpenCartYourAccountPage;
import com.wipro.atm.webdriver.pages.RegisterAccountPage;
import com.wipro.atm.webdriver.utils.Constants;
import com.wipro.atm.webdriver.utils.CustomTestListener;
import com.wipro.atm.webdriver.utils.TestDataReader;

/**
 * This class is to register a new customer and add product to the cart
 */

@Listeners(CustomTestListener.class)
public class RegistrationAndAddToCart extends ExtentReportBaseClass {
	Logger logger = LogManager.getRootLogger();
	private WebDriver driver;
	private OpenCartStartPage openCartStartPage;
	private OpenCartHomePage openCartHomePage;
	private RegisterAccountPage registerAccountPage;
	private OpenCartYourAccountPage openCartYourAccountPage;
	private OpenCartProductDetailPage openCartProductDetailPage;
	public static TestDataReader dataReader = new TestDataReader();
	LinkedHashMap<String, String> dataMap = new LinkedHashMap<String, String>();

	/**
	 * below method will start the browser based on the browser preference
	 */
	@BeforeClass(description = "Start browser")
	public void startBrowserTest() {
		logger.info("Test Execution Started For OpenCart Test");
		logger.info("Browser name : " + Constants.Browser);
		driver = Driver.intializeDriver(Constants.Browser);
		openCartStartPage = new OpenCartStartPage(driver);
		openCartStartPage.openOpenCartPage(driver, Constants.OPEN_CART);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Constants.WAIT_TIME, TimeUnit.SECONDS);
	}

	/**
	 * below method will proceed for new registration by clicking on crate new
	 * account btn
	 */

	@Test(description = "Proceeding for new registration by clicking on create new account")
	public void proceedForRegistrationTest() {
		extentLogger = extent.startTest("proceedForRegistrationTest");
		openCartHomePage = new OpenCartHomePage(driver);
		openCartHomePage.openCartCreateAnAccount();
		logger.info("User has successfully navigated to the Registration page");
		extentLogger.log(LogStatus.INFO,"User has successfully navigated to the Registration page");
		extentLogger.log(LogStatus.PASS,"Test Case (proceedForRegistrationTest) is Passed");
	}

	/**
	 * below method will provide all the necessary details which is required to
	 * craete an account
	 */

	@Test(dependsOnMethods = "proceedForRegistrationTest", description = "Provide all mandatory new user details")
	public void setNewUserDetailsTest() {
		extentLogger = extent.startTest("setNewUserDetailsTest");
		registerAccountPage = new RegisterAccountPage(driver);
		try {
			dataMap = dataReader.getEntityData(this.getClass().getSimpleName(),"UserDetails");
			String openCartFN = dataMap.get("FIRST_NAME");
			String openCartLN = dataMap.get("LAST_NAME");
			String openCartTel = dataMap.get("TEL_NO");
			String openCartAdd = dataMap.get("ADDRESS");
			String openCartCity = dataMap.get("CITY");
			String openCartPC = dataMap.get("POST_CODE");
			String openCartCountry = dataMap.get("COUNTRY");
			String openCartState = dataMap.get("STATE");
			String openCartPwd = dataMap.get("PASSWORD");
			String openCartConPwd = dataMap.get("CONFIRM_PASSWORD");
			String openCartConAccountCreatMsg = dataMap.get("ACCOUNTCREATED_MSG");

			User user = new User(openCartFN, openCartLN, Constants.Email,
					openCartTel, openCartAdd, openCartCity, openCartPC,
					openCartCountry, openCartState, openCartPwd, openCartConPwd);
			registerAccountPage.fillRegistrationFormAndSubmit(user,openCartConAccountCreatMsg);
			Assert.assertTrue(registerAccountPage.isElementDisplayed());
			logger.info("New User has been successfully created for Open cart");
			extentLogger.log(LogStatus.INFO,"New User has been successfully created for Open cart");
			extentLogger.log(LogStatus.PASS,"Test Case (setNewUserDetailsTest) is Passed");
		} catch (Exception e) {
			extentLogger.log(LogStatus.ERROR, "Failed to read Excel file");
			logger.error(e.getMessage());
		}
	}

	/**
	 * below method will navigate back to the home page
	 */

	@Test(dependsOnMethods = "setNewUserDetailsTest", description = "Navigate to the Home page")
	public void navigateToHomePageTest() {
		extentLogger = extent.startTest("navigateToHomePageTest");
		openCartYourAccountPage = new OpenCartYourAccountPage(driver);
		openCartYourAccountPage.navigateToHomePage();
		logger.info("User has been successfully navigated to the home page");
		extentLogger.log(LogStatus.INFO,"User has been successfully navigated to the home page");
		extentLogger.log(LogStatus.PASS,"Test Case (navigateToHomePageTest) is Passed");
	}

	/**
	 * below method will navigate to the coming soon product detail page
	 */

	@Test(dependsOnMethods = "navigateToHomePageTest", description = "Navigate to auto populated adv. product details page")
	public void navigateToCommingSoonProductTest() {
		extentLogger = extent.startTest("navigateToCommingSoonProductTest");
		openCartYourAccountPage = new OpenCartYourAccountPage(driver);
		openCartYourAccountPage.navigateToProductDetailPage();
		logger.info("User has been successfully navigated to auto populated adv. product details page");
		extentLogger.log(LogStatus.INFO,"User has been successfully navigated to auto populated adv. product details page");
		extentLogger.log(LogStatus.PASS,"Test Case (navigateToCommingSoonProductTest) is Passed");
	}

	/**
	 * below method will add the selected product to the wish list
	 */
	@Test(dependsOnMethods = "navigateToCommingSoonProductTest", description = "Add product to the wish list")
	public void addProductToTheWishListTest() {
		extentLogger = extent.startTest("addProductToTheWishListTest");
		openCartProductDetailPage = new OpenCartProductDetailPage(driver);
		openCartProductDetailPage.addProductToWistList();
		logger.info("Product is successfully added to the wish list");
		extentLogger.log(LogStatus.INFO,"Product is successfully added to the wish list");
		extentLogger.log(LogStatus.PASS,"Test Case (addProductToTheWishListTest) is Passed");
	}

	/**
	 * below method will add the product to the cart
	 */

	@Test(dependsOnMethods = "addProductToTheWishListTest", description = "Add product to the cart and update the price")
	public void addProductToTheCartTest() {
		extentLogger = extent.startTest("addProductToTheCartTest");
		try {
			dataMap = dataReader.getEntityData(this.getClass().getSimpleName(),"ProdDetail");
			String openCartProdPrice = dataMap.get("PRODUCT_PRICE");
			openCartProductDetailPage.addProductToTheCart(openCartProdPrice);
			Assert.assertTrue(openCartProductDetailPage.isElementDisplayed());
			logger.info("Successfully converted the price to Pound and added item to the cart");
			extentLogger.log(LogStatus.INFO,"Successfully converted the price to Pound and added item to the cart");
			extentLogger.log(LogStatus.PASS,"Test Case (addProductToTheCartTest) is Passed");
		} catch (Exception e) {
			extentLogger.log(LogStatus.ERROR, "Failed to read Excel file");
			logger.error(e.getMessage());
		}
	}

	/**
	 * below method will remove the product from the wish list
	 */

	@Test(dependsOnMethods = "addProductToTheCartTest", description = "Remove product from wish list")
	public void removeProductFromWishListTest() {
		extentLogger = extent.startTest("removeProductFromWishListTest");
		try {
			dataMap = dataReader.getEntityData(this.getClass().getSimpleName(),"ProdDetail");
			String openCartYourAccount = dataMap.get("YOUR_ACCOUNT");
			openCartProductDetailPage.removeProductFromWishList(openCartYourAccount);
			logger.info("Successfully removed the product from wish list ");
			extentLogger.log(LogStatus.INFO,"Successfully removed the product from wish list");
			extentLogger.log(LogStatus.PASS,"Test Case (removeProductFromWishListTest) is Passed");
		} catch (Exception e) {
			extentLogger.log(LogStatus.ERROR, "Failed to read Excel file");
			logger.error(e.getMessage());
		}
	}

	/**
	 * below method will log out from the application
	 */

	@AfterClass(description = "Logout")
	public void stopBrowserTest() {
		openCartHomePage = new OpenCartHomePage(driver);
		openCartHomePage.openCartLogout();
		logger.info("Execution completed for Open Cart test hence logging out from the application");
		if (null != driver) {
			driver.quit();
		}
	}

}
