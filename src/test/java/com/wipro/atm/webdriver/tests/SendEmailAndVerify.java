package com.wipro.atm.webdriver.tests;

import com.relevantcodes.extentreports.LogStatus;
import com.wipro.atm.webdriver.driver.Driver;
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
public class SendEmailAndVerify extends ExtentReportBaseClass {
    Logger logger = LogManager.getRootLogger();
    private WebDriver driver;
    private OutlookStartPage outlookStartPage;
    private OutlookSignInPage outlookSignInPage;
    private OutlookComposeMailPage outlookComposeMailPage;

    /**
     * below method will start the browser based on the browser preferance
     */

    @BeforeClass(description = "Start browser")
    public void startBrowserTest() {
        logger.info("Test Execution Started For outlook Test");
        driver = Driver.intializeDriver(Constants.Browser);
        outlookStartPage = new OutlookStartPage(driver);
        outlookStartPage.openOutlookApp(driver, Constants.OUTLOOK_URL);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Constants.WAIT_TIME, TimeUnit.SECONDS);
    }

    /**
     * below method is to login to the outlook application
     */

    @Test(description = "Login to outlook application")
    public void loginToOutlookTest() {
        extentLogger = extent.startTest("loginToOutlookTest");
        outlookSignInPage = new OutlookSignInPage(driver);
        User user = new User(Constants.OUTLOOK_LOGINEMAIL, Constants.OUTLOOK_PASSWORD);
        outlookSignInPage.loginToOutlook(user);
        Assert.assertTrue(outlookSignInPage.isElementDisplayed());
        logger.info("User has successfully login to outlook");
        extentLogger.log(LogStatus.INFO,"User has successfully login to outlook");
        extentLogger.log(LogStatus.PASS, "Test Case (loginToOutlookTest) is Passed");
    }

    /**
     * below method will compose new email and send to the recipient
     */

    @Test(dependsOnMethods = "loginToOutlookTest", description = "Compose a new email")
    public void composeANewEmailAndSendTest() {
        extentLogger = extent.startTest("composeANewEmailAndSendTest");
        outlookComposeMailPage = new OutlookComposeMailPage(driver);
        outlookComposeMailPage.composeANewEmailAndSend(Constants.OUTLOOK_EMAIL, Constants.MessageSubject, Constants.MessageBody);
        logger.info("Email has been composed and has been sent successfully ");
        extentLogger.log(LogStatus.INFO,"Email has been composed and has been sent successfully");
        extentLogger.log(LogStatus.PASS, "Test Case (composeANewEmailAndSendTest) is Passed");
    }

    /**
     * below method is to search email and delete the same
     */

    @Test(dependsOnMethods = "composeANewEmailAndSendTest", description = "search email and delete the same")
    public void searchAndDeleteEmailTest() {
        extentLogger = extent.startTest("searchAndDeleteEmailTest");
        outlookComposeMailPage.searchAndDeleteEmail(Constants.MessageSubject);
        logger.info("Email has been received successfully and deleted after verification ");
        extentLogger.log(LogStatus.INFO,"Email has been received successfully and deleted after verification ");
        extentLogger.log(LogStatus.PASS, "Test Case (searchAndDeleteEmailTest) is Passed");
    }

    /**
     * below method will log out from the application
     */

    @AfterClass(description = "Logout")
    public void stopBrowserTest() {
        outlookStartPage.outlookLogout();
        logger.info("Execution completed for Outlook test hence logging out from the application");
        if (null != driver) {
            driver.quit();
        }
    }
}
