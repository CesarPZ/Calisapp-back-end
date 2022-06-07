package com.calisapp.it;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.MalformedURLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.managers.ChromeDriverManager;

public class RegisterTestIT {
	public static WebDriver driver=null;
	public static By buttonRegister = By.className("btn");

	public RegisterTestIT() {}
	
	@Before
    public void initializeDriver() throws MalformedURLException {		
		ChromeDriverManager.getInstance().setup();
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized");
		
        driver = new ChromeDriver(options);   
	}
    
    @After
    public void finishDriver() {
        driver.close();
    }

    public void getUrlRegister() {
        driver.get("http://localhost:4200/#/register");
    }
    
    public void setInputData (String mail, String name, String password) {
    	driver.findElement(By.name("mail")).sendKeys(mail); 
		driver.findElement(By.name("name")).sendKeys(name);
		driver.findElement(By.name("password")).sendKeys(password);
    }
    
    @Test
    public void whenIEnterAEmailAndNameAndPasswordValidTheRegisterButtonIsEnabled() throws Exception {      
    	getUrlRegister();
        
    	setInputData("test@gmail.com", "testff", "123456");
        
        boolean value = driver.findElement(buttonRegister).isEnabled();

        assertEquals(value, true);
    }
    
    @Test
    public void whenIEnterAnInvalidMailTheRegisterButtonIsDisabled() throws Exception { 
    	getUrlRegister();
        
    	setInputData("test", "test", "123456");
        
        boolean value = driver.findElement(buttonRegister).isEnabled();

        assertEquals(value, false);
    }
    
    @Test
    public void whenIEnterAnInvalidNameTheRegisterButtonIsDisabled() throws Exception { 
    	getUrlRegister();
        
    	setInputData("test@gmail.com", "tes", "123456");
		
        boolean value = driver.findElement(buttonRegister).isEnabled();
        
        assertEquals(value, false);
    }
    
    @Test
    public void whenIEnterAnInvalidPasswordTheRegisterButtonIsDisabled() throws Exception { 
    	getUrlRegister();
        
    	setInputData("test@gmail.com", "test", "123");
		
        boolean value = driver.findElement(buttonRegister).isEnabled();
        
        assertEquals(value, false);
    }
    
    @Test
    public void whenIEnterAnEmailThatAlreadyExistsThenErrorMessage() throws Exception { 
    	getUrlRegister();
        String mail = "jmdicostanzo11@gmail.com";
        
        setInputData(mail, "test", "123456");
		 
		WebElement login = driver.findElement(buttonRegister);
        login.click();
        
        WebElement alert = new WebDriverWait(driver, 10)
        		.until(ExpectedConditions.visibilityOfElementLocated(By.className("alert")));
     
        String messageExpCurrent = alert.getText();
        String messageExp = "El usuario con el email: " + mail + " ya existe!!!";

        assertEquals(messageExp, messageExpCurrent);  
    }
    /* Guarda el usuario en la base de datos
    @Test
    public void whenIEnterAllTheDataWellAndClickTheRegisterButtonTheUserIsRegistered() throws Exception { 
    	driver.get("http://localhost:4200/#/register");
        
        setInputData("test3@gmail.com", "test", "123456");
        
		WebElement login = driver.findElement(buttonRegister);
        login.click();
        
        new WebDriverWait(driver, 10)
		.until(ExpectedConditions.urlMatches("http://localhost:4200/#/home"));
        
        String currentUrl="http://localhost:4200/#/home";
        String expectedUrl= driver.getCurrentUrl();
        
        assertEquals(expectedUrl,currentUrl); 
    }*/
    
    @Test
    public void whenClickLoginLinkRedirectToLoginPage() throws Exception { 
    	getUrlRegister();
     
    	WebElement loginLink = driver.findElement(By.linkText("¿Ya tienes una cuenta? Inicia sesión"));
    	loginLink.click();        

        String currentUrl="http://localhost:4200/#/login";
        String expectedUrl= driver.getCurrentUrl();
        
        assertEquals(expectedUrl,currentUrl);     
    }
    
    @Test
    public void fromTheHomePageIWantToGoToTheRegisterPage() throws Exception { 
    	driver.get("http://localhost:4200/#/home");
     
    	//El usuario clikea en el link Ingresar para registrarse
    	WebElement ingresarLink = driver.findElement(By.id("getInTo"));
    	ingresarLink.click(); 
    	    	
    	//El usuario clikea en el link registrarse
    	WebElement registerLink = driver.findElement(By.id("registerLink"));
    	registerLink.click();     

        String currentUrl="http://localhost:4200/#/register";
        String expectedUrl= driver.getCurrentUrl();
        
        assertEquals(expectedUrl,currentUrl);     
    }
}
