package com.calisapp.it;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitElement {
	public static WebElement waitForElementToBeClickable (WebDriver driver, Integer seconds, By locator) {
    	WebElement element = new WebDriverWait(driver, seconds)
    			.until(ExpectedConditions.elementToBeClickable(locator)); 
		
    	return element;
    }
	
	public static WebElement waitForVisibilityOfElementLocated (WebDriver driver, Integer seconds, By locator) {
		WebElement element = new WebDriverWait(driver, seconds)
	    		.until(ExpectedConditions.visibilityOfElementLocated(locator));
		
		return element;
	}
	
	public static void waitForUrlMatches (WebDriver driver, Integer seconds, String url) {
		new WebDriverWait(driver, seconds)
			.until(ExpectedConditions.urlMatches(url));
	}
}
