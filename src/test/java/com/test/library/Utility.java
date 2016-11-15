package com.test.library;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Utility {

	public static void doClick(Actions actions, WebElement clickObject){
		String runEnv = System.getProperty("runEnv");

		if(System.getProperty("runEnv").equals("iOSPhone") || System.getProperty("runEnv").equals("iOSTab") || System.getProperty("runEnv").equals("Safari")){
			clickObject.click();
		}else{
			actions.click(clickObject).build().perform();
		}
	}
}
