package com.test.pagefactory;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PageFactoryHome {
	public WebDriver driver;

	//Free Text Search elements Starts here

	@FindBy(xpath="//*[@id='top']//div[2]/div[2]/div[2]/div/form/input")
	public WebElement searchTextBox;


	@FindBy(xpath="//input[@class='header_right-block_searchbox_input ng-valid ng-dirty' and @aria-expanded='true']")
	public WebElement searchTextBoxSuggestions;

	@FindBy(xpath="//div[@id='top']/div/header/div[2]/div[2]/div[2]/div/form/button")
	public WebElement searchButton;

	@FindBy(xpath="//div[@class='message-box_text']/a[1]")
	public WebElement findServiceLink;

	@FindBy(xpath="//div[@class='message-box_text']/a[2]")
	public WebElement guidedSearchLink;

	@FindBy(css="h1")
	public WebElement searchResultsHeader;

	@FindBy(css="div.search_article_showing-results.ng-scope")
	public WebElement showResultsHeader;
	
	@FindBy(css="div.search_article_image-container > img")
	public WebElement showResultsImage;
	
	@FindBy(css="div.search_article__result-title > h3 > a")
	public WebElement showResultsTitle;
	@FindBy(css="div.search_article__web-content ")
	public WebElement showResultsDescription;
	

	
	//@FindBy(css="div.search_article_result_container.ng-scope")
	@FindBy(css="div.search_article_result_container.ng-scope")
	public List<WebElement> noOfResults;
	// noOfResults css path is different when we search based on ID
	@FindBy(css="div.search_article__result-title > h3")
	public List<WebElement> noOfResults_ID;
	
	@FindBy(css="div.search_article_showing-results.ng-scope")
	public WebElement indivResult;

	@FindBy(xpath="//div[@class='search_article_main-result_body ng-scope']/a[@class='video-box ng-scope']/div[1]/span")
	public WebElement videoIcon;

	@FindBy(css="a.hsf_page-item.ng-binding.ng-scope")
	public List<WebElement> noOfPages;

	@FindBy(css="i.hsf-results_pagination-prev-icon")
	public WebElement previousButton;

	//@FindBy(xpath="//div[@class='search_article_main-result_body ng-scope']/div[1]/div[2]/a")
	@FindBy(css=".search_article__web-url a")
	public List<WebElement> noOfPatnerLinks;

	@FindBy(css=".search_article_image-container img")
	public List<WebElement> searchResultImages;
	
	@FindBy(css=".search_article__web-content")
	public List<WebElement> listSearchDescripion;


	@FindBy(css="h2.ng-binding")
	public WebElement errMsgHeader;

	@FindBy(css="p.ng-scope")
	public WebElement didYouMeanMsg;

	@FindBy(css="p.ng-scope > a.ng-binding")
	public WebElement didYouMeanMsgLink;

	//Free Text Search elements Ends here

		
		
}


