package com.wipro.atm.webdriver.utils;

import java.util.Date;

public class Constants {

	private Constants() {
	}

	public static final String Browser = "chrome";
	public static final String OPEN_CART = "http://10.207.182.108:81/opencart/";
	public static final String TESTDATAPATH = "./ScenarioData/TestData.xls";
	public static String Email;
	public static final String OUTLOOK_URL = "https://outlook.office.com/owa/?realm=wipro.com";
	public static final int NO_OF_EXEC = 1;
	public static final int WAIT_TIME = 16;

	public static String getRandomEmail(String firstName, String lastName) {
		firstName = firstName.toLowerCase();
		lastName = lastName.toLowerCase();
		Date currDate = new Date();
		long randomNumber = currDate.getTime();
		String domain = "@wipro.com";
		Email = firstName + lastName + randomNumber + domain;
		return Email;
	}

}
