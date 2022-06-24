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

import io.github.bonigarcia.wdm.managers.ChromeDriverManager;

public class RegisterTestIT {
	public static WebDriver driver=null;
	public static By buttonRegister = By.className("btn");
	public static By messageAlert = By.className("alert");

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
    
    @Test
    public void whenIEnterAEmailAndNameAndPasswordValidTheRegisterButtonIsEnabled() throws Exception {      
    	getUrlRegister();
        
    	TestUtils.setInputDataRegister("test@gmail.com", "testff", "123456", driver);
        
        boolean value = driver.findElement(buttonRegister).isEnabled();

        assertEquals(value, true);
    }
    
    @Test
    public void whenIEnterAnInvalidMailTheRegisterButtonIsDisabled() throws Exception { 
    	getUrlRegister();
        
    	TestUtils.setInputDataRegister("test", "test", "123456", driver);
        
        boolean value = driver.findElement(buttonRegister).isEnabled();

        assertEquals(value, false);
    }
    
    @Test
    public void whenIEnterAnInvalidNameTheRegisterButtonIsDisabled() throws Exception { 
    	getUrlRegister();
        
    	TestUtils.setInputDataRegister("test@gmail.com", "tes", "123456", driver);
		
        boolean value = driver.findElement(buttonRegister).isEnabled();
        
        assertEquals(value, false);
    }
    
    @Test
    public void whenIEnterAnInvalidPasswordTheRegisterButtonIsDisabled() throws Exception { 
    	getUrlRegister();
        
    	TestUtils.setInputDataRegister("test@gmail.com", "test", "123", driver);
		
        boolean value = driver.findElement(buttonRegister).isEnabled();
        
        assertEquals(value, false);
    }
    
    @Test
    public void whenIEnterAnEmailThatAlreadyExistsThenErrorMessage() throws Exception { 
    	getUrlRegister();
        String mail = "jmdicostanzo11@gmail.com";
        
        TestUtils.setInputDataRegister(mail, "test", "123456", driver);
		 
		WebElement login = driver.findElement(buttonRegister);
        login.click();
        
        WebElement alert = WaitElement.waitForVisibilityOfElementLocated(driver, 10, messageAlert);

        String messageExpCurrent = alert.getText();
        String messageExp = "El usuario con el email: " + mail + " ya existe!!!";

        assertEquals(messageExp, messageExpCurrent);  
    }
    /* Guarda el usuario en la base de datos
    @Test
    public void whenIEnterAllTheDataWellAndClickTheRegisterButtonTheUserIsRegistered() throws Exception { 
        getUrlRegister();
        
        TestUtils.setInputDataRegister("test@gmail.com", "test", "123456", driver);
        
		WebElement register = driver.findElement(buttonRegister);
		register.click();
        
        WaitElement.waitForUrlMatches(driver, 10, "http://localhost:4200/#/welcome");

        String currentUrl="http://localhost:4200/#/welcome";
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

        WaitElement.waitForUrlMatches(driver, 10, "http://localhost:4200/#/register");

        String currentUrl="http://localhost:4200/#/register";
        String expectedUrl= driver.getCurrentUrl();
        
        assertEquals(expectedUrl,currentUrl);     
    }
}
