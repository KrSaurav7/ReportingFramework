package com.wipro.atm.webdriver.pages;

/**
 * Below class is to throw custom exception by extending Exception class
 */

public class UserNotCreatedException extends Exception {
    /**
     * below constructor is to call Exception class constructor and return custom Exeption
     * @param message - input custom message
     */
    public UserNotCreatedException(String message) {
        super(message);

    }
}
