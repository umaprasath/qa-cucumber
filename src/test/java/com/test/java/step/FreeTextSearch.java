package com.test.java.step;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertTrue;

import java.sql.Time;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import net.thucydides.core.annotations.Step;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

import com.test.library.ManageProperties;
import com.test.library.ThinkTime;
import com.test.library.Utility;
import com.test.pagefactory.PageFactoryHome;

import cucumber.api.DataTable;

public class FreeTextSearch {

	private PageFactoryHome carPageFactory;
	private ThinkTime time;
	private Actions actions;
	private boolean isMobile;
	private Properties prop;
	private ManageProperties rp;

	public FreeTextSearch()
	{
		// Initializing the Time
		time = new ThinkTime();
		// Reading Properties file
		rp = new ManageProperties();
		prop = rp.read();
	}

	@Step("Initializing the required Page Factory")
	public void init_Factory(WebDriver driver) {
		carPageFactory =  PageFactory.initElements(driver, PageFactoryHome.class);
		actions = new Actions(driver);
	}

	@Step("Checking if runEnv is for Android or iOS so to Click the mobile menu")
	public void isMobile(WebDriver driver, String runEnv) {
		if (runEnv.equals("AndroidPhone") || runEnv.equals("iOSPhone")) {
			System.out.println("Running the tests on mobile phone.");
			isMobile=true;

		}
	}

	@Step("Verify User is able to enter text characters in the Search textbox in the header")
	public void verifyUserEntry(WebDriver driver, String runEnv,DataTable table)
	{		
		init_Factory(driver);
	
		List<List<String>> data = table.raw();
			time.sleep(2000);
			time.waits(driver,carPageFactory.searchTextBox);
			carPageFactory.searchTextBox.sendKeys(data.get(1).get(1));
			time.sleep(2000);
			time.waits(driver,carPageFactory.searchTextBox);
			assertTrue(carPageFactory.searchTextBox.getAttribute("value").equals(data.get(1).get(1)));
			
			
	}

	

	@Step("Verify on user enter in Search Textbox - list of Suggested terms are displayed")
	public void verifySuggestedTerm(WebDriver driver)
	{		
		time.sleep(2000);
		time.waits(driver,carPageFactory.searchTextBoxSuggestions);
		assertTrue(carPageFactory.searchTextBoxSuggestions.isDisplayed());	
	}


	

	@Step("Verify User is able to enter text in the Search textbox in the header")
	public void verifyUserSpecificEntry(WebDriver driver, DataTable table)
	{	
		
			List<List<String>> data = table.raw();
			time.sleep(5000);
			System.out.println("search data is  " +data.get(1).get(1));
			time.waits(driver,carPageFactory.searchTextBox);
			carPageFactory.searchTextBox.clear();
			time.waits(driver,carPageFactory.searchTextBox);
			
			carPageFactory.searchTextBox.sendKeys(data.get(1).get(1));
			time.sleep(5000);
			time.waits(driver,carPageFactory.searchTextBox);
			assertTrue(carPageFactory.searchTextBox.getAttribute("value").equals(data.get(1).get(1)));
			//click on Search button
			time.waits(driver,carPageFactory.searchButton);
			Utility.doClick(actions,carPageFactory.searchButton);
			time.waits(driver,carPageFactory.showResultsHeader);
		
	}

	@Step("Verify What is Respite Care? - featured article is displayed in Search Results")
	public void verifySearchResults(WebDriver driver, DataTable table)
	{	
		List<List<String>> data = table.raw();
		List<WebElement> results = null;
		
	
			results = carPageFactory.noOfResults;
			if(results.size()==0)
			{
			System.out.println("This is search by id");
			results = carPageFactory.noOfResults_ID;
		}
		System.out.println("results size"+results.size());
		time.sleep(5000);
		time.waits(driver,results.get(0));
		time.sleep(2000);
		System.out.println(results.get(0).findElement(By.cssSelector("a")).getText()+".........."+data.get(1).get(1));
		assertTrue(results.size()>0);
		//assertTrue(results.get(0).findElement(By.cssSelector("a")).getText().toLowerCase().contains("*"+data.get(1).get(1)+"*"));
		try{
		assertTrue(StringUtils.containsIgnoreCase(results.get(0).findElement(By.cssSelector("a")).getText(), data.get(1).get(1)));
		}
		catch(Exception e)
		{
			System.out.println(data.get(1).get(1) + "is search with CodeID");
		}
	}

	@Step("Verify display of Show links to Find a service and Guided search on the search result")
	public void verifySearchLinks(WebDriver driver)
	{	
		
		time.sleep(2000);
		time.waits(driver,carPageFactory.findServiceLink);
		assertTrue(carPageFactory.findServiceLink.isDisplayed());
		assertTrue(carPageFactory.guidedSearchLink.isDisplayed());
	}

	

	@Step("Verify User can navigate back to first Page of Search")
	public void verifyUserNavToFirstPage(WebDriver driver) {
		time.sleep(2000);
		List<WebElement> pages;
		pages=carPageFactory.noOfPages;
		time.waits(driver,pages.get(0));
		Utility.doClick(actions,pages.get(0));
		time.waits(driver,carPageFactory.showResultsHeader);
		assertTrue(carPageFactory.showResultsHeader.isDisplayed());
	}

	@Step("Verify each item shown on results displays external url (domain only) [Partner Content only]")
	public void verifyPatnerContent(WebDriver driver) {
		System.out.println("--Verify Patner Content--");
		
		time.waits(driver,carPageFactory.noOfPatnerLinks);
		List<WebElement> patnerLinks = carPageFactory.noOfPatnerLinks;
        
		System.out.println("Partner Links = " + patnerLinks.size());
		
		for (WebElement patnerLink : patnerLinks) {
			time.waits(driver,patnerLink);

			//Verify display of patner links and verify its link association
			assertTrue(patnerLink.getAttribute("href").startsWith("http"));
		}
	}

	@Step("Verify each item shown on results displays title,links to detail page,description and visual indicator for videos")
	public void verifyLocalContent(WebDriver driver) {
		System.out.println("--Verify Local Content--");
		//verify first Page details
		
		time.waits(driver,carPageFactory.noOfResults);
		List<WebElement> results = carPageFactory.noOfResults;
		
		System.out.println("No of results...."+results.size());
		
		int iseq=3;

		for (WebElement result : results) {
			
			time.sleep(2000);
			verifyDescription(driver, iseq);
			iseq++;
			}		
	}
	@Step("Verify each item shown on results displays description")
	public void verifyDescription(WebDriver driver, int iseq) {
		System.out.println("--Verify Description--");
		time.sleep(2000);
	   
		System.out.println("Description == " + driver.findElement(By.xpath("//*[@id='main-content']/div[2]/div/article/div[2]/div[2]/div[" + iseq + "]/div[2]/div/div[2]")).getText());
		//Verify description is displayed for each results
		assertTrue(driver.findElement(By.xpath("//*[@id='main-content']/div[2]/div/article/div[2]/div[2]/div[" + iseq + "]/div[2]/div/div[2]")).getText().length()!=0);
	}	

}
