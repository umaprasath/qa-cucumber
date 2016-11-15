package com.test.java.step;

import static org.junit.Assert.assertTrue;

import java.util.Properties;

import net.thucydides.core.annotations.Step;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

import com.test.java.cucumber.PageConstants;
import com.test.library.ManageProperties;
import com.test.library.ThinkTime;

/**
 * Common Steps of Carer Site Scenarios
 * 
 *
 */
public class CommonStep {
	private boolean flag=true;
	private Properties prop;
	private ManageProperties rp;
	private String pageName;

	public CommonStep()
	{
		// Initializing the Time
		// Reading Properties file
		rp = new ManageProperties();
		prop = rp.read();
		}

	
	  @Step("I am on {1}")
	    public void iGoToPage(WebDriver driver, String pageName) {
	       // driver.get(prop.getProperty(PageConstants.getFromName(pageName).getPageUrl(prop)));
		  driver.get(PageConstants.getFromName(pageName).getPageUrl(prop));
	        assertTrue(true);
			this.pageName = pageName;
	    }
	 
	 
}
