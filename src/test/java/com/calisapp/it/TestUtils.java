package com.calisapp.it;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestUtils {
	public static void userLogged(String mail, String password, WebDriver driver) {
        driver.get("http://localhost:4200/#/login");
		
        setInputData(mail, password, driver);
		
		WebElement login = driver.findElement(By.name("submitLogin"));
        login.click(); 
    }
	
	public static void setInputData (String mail, String password, WebDriver driver) {
    	driver.findElement(By.id("mat-input-0")).sendKeys(mail); 
		driver.findElement(By.id("mat-input-1")).sendKeys(password);
    }
	
	public static void userLogout(WebDriver driver) {
    	//El usuario clikea en el link Actividades Usuario
        WebElement activityUserLink = new WebDriverWait(driver, 10)
    			.until(ExpectedConditions.elementToBeClickable(By.id("activityUser")));
        activityUserLink.click();
        
        //salir de la app
        WebElement logout = new WebDriverWait(driver, 10)
			.until(ExpectedConditions.elementToBeClickable(By.id("logout"))); 
        logout.click();
    }
	
	public static void setInputDataRegister (String mail, String name, String password, WebDriver driver) {
    	driver.findElement(By.name("mail")).sendKeys(mail); 
		driver.findElement(By.name("name")).sendKeys(name);
		driver.findElement(By.name("password")).sendKeys(password);
    }
}
