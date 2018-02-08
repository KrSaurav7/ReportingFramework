package com.wipro.atm.webdriver.utils;

import java.util.Date;

public class Constants {

    private Constants() {
    }

    public static final String Browser = "chrome";
    public static final String OPEN_CART = "http://10.207.182.108:81/opencart/";
    public static  String Email;
    public static final String PASSWORD = "zxasqw!@";
    public static final String CONFIRM_PASSWORD = "zxasqw!@";
    public static final String FIRST_NAME = "Auto";
    public static final String LAST_NAME = "Test";
    public static final String TEL_NO = "1234567894";
    public static final String ADDRESS = "Gopanpally";
    public static final String CITY = "Hyderabad";
    public static final String COUNTRY= "India";
    public static final String STATE = "Andhra Pradesh";
    public static final String POST_CODE = "500081";
    public static final String ACCOUNTCREATED_MSG = "Your Account Has Been Created!";
    public static final String PRODUCT_PRICE = "£123.95";
    public static final String YOUR_ACCOUNT = "My Account";
    public static final String OPENCARTLOGIN_EMAIL = "autotest231245@wipro.com";
    public static final String OPENCARTLOGIN_PASSWORD = "Test@12345";
    public static final String PRODUCT_QTY = "2";
    public static final String ORDERCONFIRM_MSG = "Your Order Has Been Processed!";
    public static final String OUTLOOK_URL = "https://outlook.office.com/owa/?realm=wipro.com";
    public static final String OUTLOOK_LOGINEMAIL = "KU395948@wipro.com";
    public static final String OUTLOOK_EMAIL = "kumar.saurav7";
    public static final String OUTLOOK_PASSWORD = "Feb@1234590$";
    public static final String MessageSubject = "Subject for the mail";
    public static final String MessageBody = "Message to be sent to verify it has been sent successfully or not";
    public static final int NO_OF_EXEC = 1;
    public static final int WAIT_TIME = 10;


    public static String getRandomEmail(String firstName,String lastName){
        firstName=firstName.toLowerCase();
        lastName=lastName.toLowerCase();
        Date currDate = new Date();;
        long randomNumber = currDate.getTime();
        String domain = "@wipro.com";
        Email = firstName + lastName + randomNumber + domain;
        return Email;
    }


}
