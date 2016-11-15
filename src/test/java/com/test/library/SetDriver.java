package com.test.library;

import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.saucelabs.common.Utils;
import com.saucelabs.selenium.client.factory.SeleniumFactory;

public class SetDriver {

	private String sessionId;
	private String browser;
	private WebDriver driver;
	
	public WebDriver set(String runEnv , String bamboo)
	{
		try
		{
			String OS = System.getProperty("os.name").toLowerCase();
			//if (runEnv == "null" || runEnv.length() == 0) {
//			if (runEnv == "Bamboo") {
//				System.out.print("Use bamboo configured web browser.");
//				driver = SeleniumFactory.createWebDriver();
//			} else
			
		  if (bamboo.equals("true") )
			{
				System.out.print("Use bamboo configured web browser.");
				driver = SeleniumFactory.createWebDriver();
			}
			else if ( bamboo.equals("false") ){	
			
			if (runEnv.equals("Chrome") ) {
				if(OS.indexOf("mac") >= 0){
					System.out.println("indexOf is "+OS.indexOf("mac")  );
					System.setProperty("webdriver.chrome.driver","src/test/java/chromedriver");
				}else
				{
					System.setProperty("webdriver.chrome.driver","src/test/java/chromedriver.exe");
				}
				driver = new ChromeDriver();
			} 
			else if(runEnv.equals("Safari") ) {
				SafariOptions options = new SafariOptions();
						 options.setUseCleanSession(true);
				driver = new SafariDriver(options);
				//driver.manage().deleteAllCookies();
				DesiredCapabilities capabilities = DesiredCapabilities.safari();
				   capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
				 capabilities.setCapability(SafariOptions.CAPABILITY, options);
				 driver = new RemoteWebDriver(
				     new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
				 driver.manage().deleteAllCookies();
			}
			else if (runEnv.equals("AndroidPhone")) {
		        // Set up Android browser for Appium
		    	DesiredCapabilities  capabilities = new DesiredCapabilities();
		        capabilities.setCapability("deviceName","Samsung S6");
		    	
		        //capabilities.setCapability("app", "Chrome");
		    	capabilities.setCapability("platformName", "Android");
		    	// Bug on Appium with real device browser "https://github.com/appium/appium/issues/4509". Hence, using "Chrome" instead of "Browser"
		        capabilities.setCapability(CapabilityType.BROWSER_NAME, "Chrome");
		        capabilities.setCapability(CapabilityType.VERSION, "5.1");
		        capabilities.setCapability(CapabilityType.PLATFORM, "WINDOWS");
		        driver = new RemoteWebDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		    }  else if (runEnv.equals("AndroidTab")) {
		        // Set up Android browser for Appium
		    	DesiredCapabilities  capabilities = new DesiredCapabilities();
		        capabilities.setCapability("deviceName","Samsung Tab");
		        //capabilities.setCapability("app", "Chrome");
		    	capabilities.setCapability("platformName", "Android");
		    	// Bug on Appium with real device browser "https://github.com/appium/appium/issues/4509". Hence, using "Chrome" instead of "Browser"
		        capabilities.setCapability(CapabilityType.BROWSER_NAME, "Chrome");
		        capabilities.setCapability(CapabilityType.VERSION, "4.2.2");
		        capabilities.setCapability(CapabilityType.PLATFORM, "WINDOWS");
		        driver = new RemoteWebDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		    } else if (runEnv.equals("iOSPhone")) {
		    	DesiredCapabilities capabilities = new DesiredCapabilities();
		        capabilities.setCapability("deviceName", "iPhone 6");
		        capabilities.setCapability("platformName", "iOS");
		        capabilities.setCapability("platformVersion", "8.1");
		        capabilities.setCapability("browserName", "safari");
		        driver = new RemoteWebDriver(new URL("http://127.0.0.1:4723/wd/hub"),
		                capabilities);
		    } else if (runEnv.equals("iOSTab")) {
		    	DesiredCapabilities capabilities = new DesiredCapabilities();
		        capabilities.setCapability("deviceName", "iPad");
		        capabilities.setCapability("platformName", "iOS");
		        capabilities.setCapability("platformVersion", "8.1");
		        capabilities.setCapability("browserName", "safari");
		        driver = new RemoteWebDriver(new URL("http://127.0.0.1:4723/wd/hub"),
		                capabilities);
		    } else if (runEnv.equals("Sauce")) {
				DesiredCapabilities caps = DesiredCapabilities.chrome();
				java.lang.String version = Utils.readPropertyOrEnv(
						"SELENIUM_VERSION", "");
				if (!version.equals("")) {
					caps.setCapability("version", version);
				}
				caps.setCapability("platform", "Windows 7");
				caps.setCapability("version", "34");
				java.lang.String username = Utils.readPropertyOrEnv(
						"SAUCE_USER_NAME", "itorders");
				java.lang.String accessKey = Utils.readPropertyOrEnv(
						"SAUCE_API_KEY", "4095791a-cc03-41ff-93e3-4f1f615a9e13");
	
				// caps.setCapability("name", this.getClass().getName() + "." +
				// testName.getMethodName());
	
				this.driver = new RemoteWebDriver(new URL("http://" + username + ":" + accessKey + "@ondemand.saucelabs.com:4444/wd/hub"), caps);
				this.sessionId = ((RemoteWebDriver) driver).getSessionId()
						.toString();
			} else if (runEnv.equals("Firefox")) {
				driver = new FirefoxDriver();
			} else if (runEnv.equals("IE")) {
				DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
				System.setProperty("webdriver.ie.driver",
						"src/test/java/IEDriverServer.exe");
				caps.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);  
				driver = new InternetExplorerDriver();
				
			}
			}
			
			driver.manage().window().maximize();
			browser = ((RemoteWebDriver) driver).getCapabilities().getBrowserName();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return driver;
	}

}
