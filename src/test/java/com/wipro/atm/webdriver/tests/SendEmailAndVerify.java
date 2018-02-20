package com.wipro.atm.webdriver.tests;

import com.relevantcodes.extentreports.LogStatus;
import com.wipro.atm.webdriver.driver.Driver;
import com.wipro.atm.webdriver.model.User;
import com.wipro.atm.webdriver.pages.*;
import com.wipro.atm.webdriver.utils.Constants;
import com.wipro.atm.webdriver.utils.CustomTestListener;
import com.wipro.atm.webdriver.utils.TestDataReader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.LinkedHashMap;
import java.util.concurrent.TimeUnit;

@Listeners(CustomTestListener.class)
public class SendEmailAndVerify extends ExtentReportBaseClass{
    Logger logger = LogManager.getRootLogger();
    private WebDriver driver;
    private OutlookStartPage outlookStartPage;
    private OutlookSignInPage outlookSignInPage;
    private OutlookComposeMailPage outlookComposeMailPage;
    public static TestDataReader dataReader = new TestDataReader();	
	LinkedHashMap<String, String> dataMap = new LinkedHashMap<String, String>();

    /**
     * below method will start the browser based on the browser preference
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
        try{
        dataMap= dataReader.getEntityData(this.getClass().getSimpleName(),"Login");
    	String outlookUN = dataMap.get("UserName");
    	System.out.println("test is " + outlookUN );
    	String outlookPwd = dataMap.get("Pwd");
        User user = new User(outlookUN, outlookPwd);
        outlookSignInPage.loginToOutlook(user);
        Assert.assertTrue(outlookSignInPage.isElementDisplayed());
        logger.info("User has successfully login to outlook");
        extentLogger.log(LogStatus.INFO,"User has successfully login to outlook");
        extentLogger.log(LogStatus.PASS, "Test Case (loginToOutlookTest) is Passed");
        }
        catch(Exception e)
    	{
        extentLogger.log(LogStatus.ERROR, "Failed to read Excel file");
    	logger.error(e.getMessage());
    	}
    }

    /**
     * below method will compose new email and send to the recipient
     */

    @Test(dependsOnMethods = "loginToOutlookTest", description = "Compose a new email")
    public void composeANewEmailAndSendTest() {
        extentLogger = extent.startTest("composeANewEmailAndSendTest");
        outlookComposeMailPage = new OutlookComposeMailPage(driver);
        try
        {
        dataMap= dataReader.getEntityData(this.getClass().getSimpleName(),"EmailDetails");	
        String outlookEmailTo = dataMap.get("EmailTo");
    	String outlookEmailSub = dataMap.get("EmailSubject");
    	String outlookEmailBody = dataMap.get("EmailBody");
        outlookComposeMailPage.composeANewEmailAndSend(outlookEmailTo, outlookEmailSub, outlookEmailBody);
        logger.info("Email has been composed and has been sent successfully ");
        extentLogger.log(LogStatus.INFO,"Email has been composed and has been sent successfully");
        extentLogger.log(LogStatus.PASS, "Test Case (composeANewEmailAndSendTest) is Passed");
    }
        catch(Exception e)
	{
        	extentLogger.log(LogStatus.ERROR, "Failed to read Excel file");
         	logger.error(e.getMessage());
	}
    }

    /**
     * below method is to search email and delete the same
     */

    @Test(dependsOnMethods = "composeANewEmailAndSendTest", description = "search email and delete the same")
    public void searchAndDeleteEmailTest() {
        extentLogger = extent.startTest("searchAndDeleteEmailTest");
        try
        {
        dataMap= dataReader.getEntityData(this.getClass().getSimpleName(),"EmailDetails");	
    	String outlookEmailSub = dataMap.get("EmailSubject");
    	String outlookEmailBody = dataMap.get("EmailBody");
        outlookComposeMailPage.searchAndDeleteEmail(outlookEmailSub,outlookEmailBody);
        logger.info("Email has been received successfully and deleted after verification ");
        extentLogger.log(LogStatus.INFO,"Email has been received successfully and deleted after verification ");
        extentLogger.log(LogStatus.PASS, "Test Case (searchAndDeleteEmailTest) is Passed");
        }
        catch(Exception e)
	{
        	extentLogger.log(LogStatus.ERROR, "Failed to read Excel file");
         	logger.error(e.getMessage());
	}
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
