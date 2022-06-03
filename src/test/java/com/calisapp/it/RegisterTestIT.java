package com.calisapp.it;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Assert;
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

    @Test
    public void whenIEnterAEmailAndNameAndPasswordValidTheRegisterButtonIsEnabled() throws Exception {      
    	driver.get("http://localhost:4200/#/register");
        
		driver.findElement(By.name("mail")).sendKeys("test@gmail.com");
		driver.findElement(By.name("name")).sendKeys("testff");
		driver.findElement(By.name("password")).sendKeys("123456");
        
        boolean value = driver.findElement(By.className("btn")).isEnabled();

        Assert.assertEquals(value, true);
    }
    
    @Test
    public void whenIEnterAnInvalidMailTheRegisterButtonIsDisabled() throws Exception { 
    	driver.get("http://localhost:4200/#/register");
        
        driver.findElement(By.name("mail")).sendKeys("test");
		driver.findElement(By.name("name")).sendKeys("test");
		driver.findElement(By.name("password")).sendKeys("123456");
        
        boolean value = driver.findElement(By.className("btn")).isEnabled();

        Assert.assertEquals(value, false);
    }
    
    @Test
    public void whenIEnterAnInvalidNameTheRegisterButtonIsDisabled() throws Exception { 
    	driver.get("http://localhost:4200/#/register");
        
        driver.findElement(By.name("mail")).sendKeys("test@gmail.com");
		driver.findElement(By.name("name")).sendKeys("tes");
		driver.findElement(By.name("password")).sendKeys("123456");
		
        boolean value = driver.findElement(By.className("btn")).isEnabled();
        
        Assert.assertEquals(value, false);
    }
    
    @Test
    public void whenIEnterAnInvalidPasswordTheRegisterButtonIsDisabled() throws Exception { 
    	driver.get("http://localhost:4200/#/register");
        
        driver.findElement(By.name("mail")).sendKeys("test@gmail.com");
		driver.findElement(By.name("name")).sendKeys("test");
		driver.findElement(By.name("password")).sendKeys("123");
		
        boolean value = driver.findElement(By.className("btn")).isEnabled();
        
        Assert.assertEquals(value, false);
    }
    
    @Test
    public void whenIEnterAnEmailThatAlreadyExistsThenErrorMessage() throws Exception { 
    	driver.get("http://localhost:4200/#/register");
        String mail = "jmdicostanzo11@gmail.com";
        
        driver.findElement(By.name("mail")).sendKeys(mail); //Dato repetido
		driver.findElement(By.name("name")).sendKeys("test");
		driver.findElement(By.name("password")).sendKeys("1234");
		 
		WebElement login = driver.findElement(By.className("btn"));
        login.click();
        
        driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);

        WebElement message = driver.findElement(By.className("alert"));
        String messageExpCurrent = message.getText();
        String messageExp = "El usuario con el email: " + mail + " ya existe!!!";

        Assert.assertEquals(messageExp, messageExpCurrent);  
    }
    /* Guarda el usuario en la base de datos
    @Test
    public void whenIEnterAllTheDataWellAndClickTheRegisterButtonTheUserIsRegistered() throws Exception { 
    	driver.get("http://localhost:4200/#/register");
        
        driver.findElement(By.name("mail")).sendKeys("test4@gmail.com");
		driver.findElement(By.name("name")).sendKeys("test");
		driver.findElement(By.name("password")).sendKeys("123456");
        
		WebElement login = driver.findElement(By.className("btn"));
        login.click();
        
        Thread.sleep(3000);        

        String currentUrl="http://localhost:4200/#/home";
        String expectedUrl= driver.getCurrentUrl();
        
        Assert.assertEquals(expectedUrl,currentUrl);  
    }*/
    
    @Test
    public void whenClickLoginLinkRedirectToLoginPage() throws Exception { 
    	driver.get("http://localhost:4200/#/register");
     
    	WebElement loginLink = driver.findElement(By.xpath("//a[contains(text(),'Inicia sesión')]"));
    	loginLink.click();        

        String currentUrl="http://localhost:4200/#/login";
        String expectedUrl= driver.getCurrentUrl();
        
        Assert.assertEquals(expectedUrl,currentUrl);     
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
        
        Assert.assertEquals(expectedUrl,currentUrl);     
    }
}
