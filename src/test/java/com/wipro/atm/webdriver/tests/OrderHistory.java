package com.wipro.atm.webdriver.tests;

import com.relevantcodes.extentreports.LogStatus;
import com.wipro.atm.webdriver.driver.Driver;
import com.wipro.atm.webdriver.model.Product;
import com.wipro.atm.webdriver.model.User;
import com.wipro.atm.webdriver.pages.*;
import com.wipro.atm.webdriver.utils.Constants;
import com.wipro.atm.webdriver.utils.CustomTestListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;


@Listeners(CustomTestListener.class)
public class OrderHistory extends ExtentReportBaseClass {
    Logger logger = LogManager.getRootLogger();
    private WebDriver driver;
    private OpenCartStartPage openCartStartPage;
    private OpenCartSignInPage openCartSignInPage;
    private OpenCartYourAccountPage openCartYourAccountPage;
    private OpenCartHomePage openCartHomePage;
    private OpenCartProductDetailPage openCartProductDetailPage;
    private OpenCartCheckOutPage openCartCheckOutPage;

    /**
     * below method will start the browser based on the browser preferance
     */

    @BeforeClass(description = "Start browser")
    public void startBrowserTest() {
            logger.info("Test Execution Started For OpenCart Test");
            driver = Driver.intializeDriver(Constants.Browser);
            openCartStartPage = new OpenCartStartPage(driver);
            openCartStartPage.openOpenCartPage(driver, Constants.OPEN_CART);
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Constants.WAIT_TIME, TimeUnit.SECONDS);

    }
    /**
     * below method is to login to the open cart application
     */

    @Test(description = "Login to OpenCart application")
     public void loginToOpenCartTest() {
        extentLogger = extent.startTest("loginToOpenCartTest");
        openCartSignInPage = new OpenCartSignInPage(driver);
        User user = new User(Constants.OPENCARTLOGIN_EMAIL, Constants.OPENCARTLOGIN_PASSWORD);
        openCartSignInPage.loginToOpenCart(user);
        Assert.assertTrue(openCartSignInPage.isElementDisplayed());
        logger.info("User has successfully login to OpenCart");
        extentLogger.log(LogStatus.INFO, "User has successfully login to OpenCart");
        extentLogger.log(LogStatus.PASS, "Test Case (loginToOpenCartTest) is Passed");
    }

    /**
     * below method will navigate back to the home page
     */

    @Test(dependsOnMethods = "loginToOpenCartTest", description = "Navigate to the Home page")
    public void navigateToHomePageTest() {
        extentLogger = extent.startTest("navigateToHomePageTest");
        openCartYourAccountPage = new OpenCartYourAccountPage(driver);
        openCartYourAccountPage.navigateToHomePage();
        logger.info("User has been successfully navigated to the home page");
        extentLogger.log(LogStatus.INFO, "User has been successfully navigated to the home page");
        extentLogger.log(LogStatus.PASS, "Test Case (navigateToHomePageTest) is Passed");
    }

    /**
     * below method will select first feature product and verify the same in PDP page
     */

    @Test(dependsOnMethods = "navigateToHomePageTest", description = "select first feature product and verify the same in PDP page")
    public void selectFirstFeatureProductAndVerifyTheSameInPDPTest(){
        extentLogger = extent.startTest("selectFirstFeatureProductAndVerifyTheSameInPDPTest");
        openCartHomePage = new OpenCartHomePage(driver);
        openCartHomePage.selectFirstFeatureProductAndVerify();
        logger.info("Selected product from home page displayed successfully");
        extentLogger.log(LogStatus.INFO, "Selected product from home page displayed successfully");
        extentLogger.log(LogStatus.PASS, "Test Case (selectFirstFeatureProductAndVerifyTheSameInPDPTest) is Passed");
    }

    /**
     * below method will add selected product to the cart,update the QTY in cart and verify the same
     */

    @Test(dependsOnMethods = "selectFirstFeatureProductAndVerifyTheSameInPDPTest", description = "add selected product to the cart,update the QTY in cart and verify the same")
    public void addProductUpdateQtyAndVerifyQtyInCartTest(){
        extentLogger = extent.startTest("addProductUpdateQtyAndVerifyQtyInCartTest");
        openCartProductDetailPage = new OpenCartProductDetailPage(driver);
        Product productQty = new Product(Constants.PRODUCT_QTY);
        openCartProductDetailPage.addProductUpdateQtyAndVerifyQtyInCart(productQty);
        logger.info("Successfully added product to the cart, updated Qty and Verified the same");
        extentLogger.log(LogStatus.INFO, "Successfully added product to the cart, updated Qty and Verified the same");
        extentLogger.log(LogStatus.PASS, "Test Case (addProductUpdateQtyAndVerifyQtyInCartTest) is Passed");
    }

    /**
     * below method will proceed for checkout
     */

    @Test(dependsOnMethods = "addProductUpdateQtyAndVerifyQtyInCartTest", description = "Proceed for checkout")
    public void proceedForCheckoutTest(){
        extentLogger = extent.startTest("proceedForCheckoutTest");
        openCartCheckOutPage = new OpenCartCheckOutPage(driver);
        openCartCheckOutPage.proceedForCheckout();
        logger.info("Checkout actions has been completed successfully");
        extentLogger.log(LogStatus.INFO, "Checkout actions has been completed successfully");
        extentLogger.log(LogStatus.PASS, "Test Case (proceedForCheckoutTest) is Passed");
    }

    /**
     * below method will place an order and verify the success message
     */

    @Test(dependsOnMethods = "proceedForCheckoutTest", description = "Confirm an order")
    public void confirmOrderAndVerifyTest(){
        extentLogger = extent.startTest("confirmOrderAndVerifyTest");
        openCartCheckOutPage.confirmOrderAndVerify();
        logger.info("Order has been placed successfully");
        extentLogger.log(LogStatus.INFO, "Order has been placed successfully");
        extentLogger.log(LogStatus.PASS, "Test Case (confirmOrderAndVerifyTest) is Passed");
    }

    /**
     * below method is to verify whether selected product is being placed or not
     */

    @Test(dependsOnMethods = "confirmOrderAndVerifyTest", description = "verify whether selected product is being placed or not")
    public void verifyOrderInOrderHistoryTest(){
        extentLogger = extent.startTest("verifyOrderInOrderHistoryTest");
        openCartCheckOutPage.verifyOrderInOrderHistory();
        logger.info("Successfully verified the selected product in order history");
        extentLogger.log(LogStatus.INFO, "Successfully verified the selected product in order history");
        extentLogger.log(LogStatus.PASS, "Test Case (verifyOrderInOrderHistoryTest) is Passed");
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
