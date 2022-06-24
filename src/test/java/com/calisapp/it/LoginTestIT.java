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

public class LoginTestIT {
	public static WebDriver driver=null;
	public static By buttonLogin = By.name("submitLogin");
	public static By activityUserButton = By.id("activityUser");
	public static By messageAlert = By.className("alert");

	public LoginTestIT() {}
	
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
    
    public void getUrlLogin() {
        driver.get("http://localhost:4200/#/login");
    }
    
    @Test
    public void whenIEnterAValidEmailAndPasswordTheLoginButtonIsEnabled() throws Exception {      
    	getUrlLogin();
    	
    	TestUtils.setInputData("test@gmail.com", "123456", driver);
        
        boolean value = driver.findElement(buttonLogin).isEnabled();

        assertEquals(value, true);
    }
    
    @Test
    public void whenIEnterAnInvalidMailTheLoginButtonIsDisabled() throws Exception { 
    	getUrlLogin();
    	
    	TestUtils.setInputData("test", "123456", driver);
        
        boolean value = driver.findElement(buttonLogin).isEnabled();

        assertEquals(value, false);
    }
    
    @Test
    public void whenIEnterAnInvalidPasswordTheLoginButtonIsDisabled() throws Exception { 
    	getUrlLogin();        
    	
    	TestUtils.setInputData("test@gmail.com", "123", driver);
		
        boolean value = driver.findElement(buttonLogin).isEnabled();
        
        assertEquals(value, false);
    }

    @Test
    public void whenIEnterAllTheDataWellAndClickTheLoginButtonTheUserIsLogged() throws Exception { 
    	getUrlLogin();
    	
    	TestUtils.setInputData("jmdicostanzo11@gmail.com", "1234", driver);
    	
		WebElement login = driver.findElement(buttonLogin);
        login.click();
             
        WaitElement.waitForUrlMatches(driver, 10, "http://localhost:4200/#/welcome");

        String currentUrl="http://localhost:4200/#/welcome";
        String expectedUrl= driver.getCurrentUrl();
        
        assertEquals(expectedUrl,currentUrl);
    }
    
    @Test
    public void whenEnterIncorrectDataThenErrorMessage() throws Exception { 
    	getUrlLogin();
    	
    	TestUtils.setInputData("jmdicostanzo11@gmail.com", "12345", driver); //password incorrecto
		 
		WebElement login = driver.findElement(buttonLogin);
        login.click();
   
        WebElement alert = WaitElement.waitForVisibilityOfElementLocated(driver, 10, messageAlert);

        String messageExpCurrent = alert.getText();
        String messageExp = "Ingresó mal los datos";

        assertEquals(messageExp, messageExpCurrent);  
    }
    
    @Test
    public void whenClickRegisterLinkRedirectToSignUpPage() throws Exception { 
    	getUrlLogin();
     
    	WebElement registerLink = driver.findElement(By.linkText("¿Aún no tienes una cuenta? Regístrate"));
    	registerLink.click();        

        String currentUrl="http://localhost:4200/#/register";
        String expectedUrl= driver.getCurrentUrl();
        
        assertEquals(expectedUrl,currentUrl);     
    }
    
    @Test
    public void fromTheHomePageIWantToGoToTheLoginPage() throws Exception { 
    	driver.get("http://localhost:4200/#/home");
     
    	//El usuario clikea en el link Ingresar para loguearse
    	WebElement ingresarLink = driver.findElement(By.id("getInTo"));
    	ingresarLink.click(); 
    	    	
    	//El usuario clikea en el link Iniciar Sesión
    	WebElement loginLink = driver.findElement(By.id("loginLink"));
    	loginLink.click();     

        WaitElement.waitForUrlMatches(driver, 10, "http://localhost:4200/#/login");

        String currentUrl="http://localhost:4200/#/login";
        String expectedUrl= driver.getCurrentUrl();
        
        assertEquals(expectedUrl,currentUrl);     
    }
    
    @Test
    public void whenTheUserIsLoggedTheLogoutButtonIsVisible() throws Exception { 
    	TestUtils.userLogged("jmdicostanzo11@gmail.com", "1234", driver);
                
        WaitElement.waitForUrlMatches(driver, 10, "http://localhost:4200/#/welcome");

        driver.getCurrentUrl();
        
        //El usuario clikea en el link Actividades Usuario        
        WebElement activityUserLink = WaitElement.waitForElementToBeClickable(driver, 10, activityUserButton);
        activityUserLink.click();
        
        //Se testea que el boton de salir es visible cuando el usuario esta logueado
        boolean isPresent = driver.findElement(By.id("logout")).isDisplayed();

        assertEquals(isPresent, true);
    }
    
    @Test
    public void whenTheUserNotIsLoggedTheLogoutButtonIsNotVisible() throws Exception { 
        driver.get("http://localhost:4200/#/home");
      
        WaitElement.waitForUrlMatches(driver, 10, "http://localhost:4200/#/home");
        
        //Se testea que el boton de salir no es visible cuando el usuario no esta logueado
        Boolean isPresent = driver.findElements(By.id("logout")).size() > 0;

        assertEquals(isPresent, false);
    }
    
    @Test
    public void WhenTheUserLogoutTheLoginButtonIsVisible() throws Exception { 
    	TestUtils.userLogged("jmdicostanzo11@gmail.com", "1234", driver); 
        driver.getCurrentUrl();
        TestUtils.userLogout(driver);

    	//Se testea que al salir el boton de ingresar se hace visible
    	boolean ingresarLink = driver.findElement(By.id("getInTo")).isDisplayed();
        assertEquals(ingresarLink, true);
        
        //El boton de salir desaparece
        Boolean isPresent = driver.findElements(By.id("logout")).size() > 0;
        assertEquals(isPresent, false);
    }
}
