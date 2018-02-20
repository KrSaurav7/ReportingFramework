package com.wipro.atm.webdriver.driver;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

public class IEDriverCreator extends WebDriverCreator {

    WebDriver driver;
    String url;
    @Override
    public WebDriver factoryMethod() {
        System.setProperty("webdriver.ie.driver", ".\\src\\main\\resources\\drivers\\IEDriverServer.exe");
         driver = new InternetExplorerDriver();
         return driver;
         /**
        url = "http://10.159.34.101:4444/wd/hub";
        try {
            DesiredCapabilities desiredCapabilities = DesiredCapabilities.internetExplorer();
            desiredCapabilities.setPlatform(Platform.WINDOWS);
            driver = new RemoteWebDriver(new URL(url), desiredCapabilities);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return driver;
        */
    }
}
