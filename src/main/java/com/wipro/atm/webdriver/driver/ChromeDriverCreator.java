package com.wipro.atm.webdriver.driver;

import java.net.URL;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;



public class ChromeDriverCreator extends WebDriverCreator {
	 WebDriver driver;
	 String url;
	@Override
	public WebDriver factoryMethod() {		
		System.setProperty("webdriver.chrome.driver", ".\\src\\main\\resources\\drivers\\chromedriver.exe");
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("disable-infobars");
		capabilities.setCapability(ChromeOptions.CAPABILITY, options);
		driver = new ChromeDriver(capabilities);
		return driver;
	 /**
		 url = "http://10.159.34.101:4444/wd/hub";
	        try {
	            DesiredCapabilities capabilities = new DesiredCapabilities();
	            capabilities.setBrowserName("chrome");
	            capabilities.setPlatform(Platform.WINDOWS);
	            ChromeOptions options = new ChromeOptions();
	            options.addArguments("disable-infobars");
	            capabilities.setCapability(ChromeOptions.CAPABILITY, options);
	            driver = new RemoteWebDriver(new URL(url), capabilities);
	        }catch(Exception e){
	            e.printStackTrace();
	        }
	        return driver;
	        */
	}
}

	