package com.test.java.cucumber;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;

import org.junit.Assert;
import static org.junit.Assert.assertTrue;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import com.opera.core.systems.scope.services.desktop.SystemInput;
import com.test.java.step.FreeTextSearch;
import com.test.java.step.CommonStep;
import com.test.library.ManageProperties;
import com.test.library.SetDriver;

import org.apache.commons.io.FileUtils;

import cucumber.api.DataTable;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;


/**
 * Scenarios of Carer Site
 * 
 */
public class TestStep  {

	@Managed private WebDriver driver;
	private String runEnv,bamboo;
	protected Properties prop;
	protected ManageProperties rp;
	private SetDriver setDriver = new SetDriver() ;
	Actions actions;
	private String pageName;
	@Steps private FreeTextSearch carFreeText;

	@Steps private CommonStep commonSteps;
	



	@Before
	public void doBefore() throws Exception {
     
		runEnv = System.getProperty("runEnv");
		bamboo = System.getProperty("bamboo");
		System.out.print("runEnv: " + runEnv +"  ");
		driver=setDriver.set(runEnv,bamboo);		
		rp = new ManageProperties();
		prop = rp.read();
		actions = new Actions(driver);
		if(runEnv==null){
			runEnv="SauceLabs";
			System.out.println("If the run env is Bamboo configured, setting the runEnv to :"+runEnv);
		}

	}
	//-------------------- Common Given Step --------------------//
	@Given("^I go to \"(.*?)\" page$")
	public void iGoToPage(String pageName) {
		this.pageName = pageName;
		commonSteps.iGoToPage(driver, pageName);
		System.out.println("The name of the page is......"+pageName);
		

	}

	/**
	 * Navigation to Home Page - Common Given Step
	 * @param pageName - page name to navigate
	 */
	
	@Given("^I go to Carers \"(.*?)\" page$")
	public void i_go_to_Carers_page(String pageName){
		commonSteps.iGoToPage(driver, pageName);
		System.out.println("The name of the page is......"+pageName);
	}

	 
	//----------Steps to validate Article starts here ------------------------------//
	



	//----------------------Free Text search Starts here ----------------------------//


	@When("^User enters first three character$")
	public void user_enters_first_three_character(DataTable arg1){
		carFreeText.verifyUserEntry(driver, runEnv, arg1);   
	}

	@Then("^should display list of suggested terms$")
	public void should_display_list_of_suggested_terms() {
		carFreeText.verifySuggestedTerm(driver);
	}

	@When("^User enters search text character$")
	public void user_enters_search_text_character(DataTable arg1)  {
		carFreeText.verifyUserSpecificEntry(driver, arg1);	
	}

	@Then("^should display featured article as results$")
	public void should_display_featured_article_as_results(DataTable arg1) {
		carFreeText.verifySearchResults(driver, arg1);
	}
	
	@Then("^verify display of Show links to Find a service and Guided search on the search result$")
	public void verify_display_of_Show_links_to_Find_a_service_and_Guided_search_on_the_search_result() {
		carFreeText.verifySearchLinks(driver);
	}

	
	
	
	//----------------------Free Text search Ends here ----------------------------//
	
	
	   
	   
	@After
	public void doAfter() {
		//File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        // now save the screenshto to a file some place
		//Timestamp currentTimestamp = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());

        //try {
		//	FileUtils.copyFile(scrFile, new File("/Users/bharathi.chaluvadi/Projects/Screenshots/screenshots"+currentTimestamp+".jpeg"));
       // } catch (IOException e) {
		//	System.out.println("we got error in screen shot capturing");
		//	e.printStackTrace();
		//}

		driver.close();
		driver.quit();
	}


}
