package com.calisapp.it;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.MalformedURLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.managers.ChromeDriverManager;

public class LoginTestIT {
	public static WebDriver driver=null;
	public static By buttonLogin = By.name("submitLogin");
	
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
    
    public void userLogged() {
    	getUrlLogin();
      
        setInputData("jmdicostanzo11@gmail.com", "1234");
		
		WebElement login = driver.findElement(buttonLogin);
        login.click(); 
    }
    
    public void getUrlLogin() {
        driver.get("http://localhost:4200/#/login");
    }
    
    public void setInputData (String mail, String password) {
    	driver.findElement(By.id("mat-input-0")).sendKeys(mail); 
		driver.findElement(By.id("mat-input-1")).sendKeys(password);
    }
    
    @Test
    public void whenIEnterAValidEmailAndPasswordTheLoginButtonIsEnabled() throws Exception {      
    	getUrlLogin();
    	
    	setInputData("test@gmail.com", "123456");
        
        boolean value = driver.findElement(buttonLogin).isEnabled();

        assertEquals(value, true);
    }
    
    @Test
    public void whenIEnterAnInvalidMailTheLoginButtonIsDisabled() throws Exception { 
    	getUrlLogin();
    	
    	setInputData("test", "123456");
        
        boolean value = driver.findElement(buttonLogin).isEnabled();

        assertEquals(value, false);
    }
    
    @Test
    public void whenIEnterAnInvalidPasswordTheLoginButtonIsDisabled() throws Exception { 
    	getUrlLogin();        
    	
    	setInputData("test@gmail.com", "123");
		
        boolean value = driver.findElement(buttonLogin).isEnabled();
        
        assertEquals(value, false);
    }

    @Test
    public void whenIEnterAllTheDataWellAndClickTheLoginButtonTheUserIsLogged() throws Exception { 
    	getUrlLogin();
    	
    	setInputData("jmdicostanzo11@gmail.com", "1234");
    	
		WebElement login = driver.findElement(buttonLogin);
        login.click();
        
        new WebDriverWait(driver, 10)
        		.until(ExpectedConditions.urlMatches("http://localhost:4200/#/home"));
     
        String currentUrl="http://localhost:4200/#/home";
        String expectedUrl= driver.getCurrentUrl();
        
        assertEquals(expectedUrl,currentUrl);
    }
    
    @Test
    public void whenEnterIncorrectDataThenErrorMessage() throws Exception { 
    	getUrlLogin();
    	
        setInputData("jmdicostanzo11@gmail.com", "12345"); //password incorrecto
		 
		WebElement login = driver.findElement(buttonLogin);
        login.click();
        
        WebElement alert = new WebDriverWait(driver, 10)
        		.until(ExpectedConditions.visibilityOfElementLocated(By.className("alert")));
     
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

        String currentUrl="http://localhost:4200/#/login";
        String expectedUrl= driver.getCurrentUrl();
        
        assertEquals(expectedUrl,currentUrl);     
    }
    
    @Test
    public void whenTheUserIsLoggedTheLogoutButtonIsVisible() throws Exception { 
    	userLogged();
        
        new WebDriverWait(driver, 10)
        	.until(ExpectedConditions.urlMatches("http://localhost:4200/#/home"));
        
        driver.getCurrentUrl();
        
        //Se testea que el boton de salir es visible cuando el usuario esta logueado
        boolean isPresent = driver.findElement(By.id("logout")).isDisplayed();

        assertEquals(isPresent, true);
    }
    
    @Test
    public void whenTheUserNotIsLoggedTheLogoutButtonIsNotVisible() throws Exception { 
        driver.get("http://localhost:4200/#/home");
        
        new WebDriverWait(driver, 10)
        	.until(ExpectedConditions.urlMatches("http://localhost:4200/#/home"));    

        //Se testea que el boton de salir no es visible cuando el usuario no esta logueado
        Boolean isPresent = driver.findElements(By.id("logout")).size() > 0;

        assertEquals(isPresent, false);
    }
    
    @Test
    public void WhenTheUserLogoutTheLoginButtonIsVisible() throws Exception { 
        JavascriptExecutor js = (JavascriptExecutor) driver;

        userLogged(); 
        
        driver.getCurrentUrl();

        //salir de la app
        WebElement logout = new WebDriverWait(driver, 10)
			.until(ExpectedConditions.elementToBeClickable(By.id("logout"))); 
        logout.click();

        WebElement outButton = driver.findElement(By.id("outButton"));
        js.executeScript("arguments[0].click();", outButton);

    	//Se testea que al salir el boton de ingresar se hace visible
    	boolean ingresarLink = driver.findElement(By.id("getInTo")).isDisplayed();
        assertEquals(ingresarLink, true);
        
        //El boton de salir desaparece
        Boolean isPresent = driver.findElements(By.id("logout")).size() > 0;
        assertEquals(isPresent, false);
    }
}




//      driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
//      Thread.sleep(3000);        

      
    //   WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
   //    WebElement message = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("alert")));
      
  //     WebDriverWait alertAwaiter = new WebDriverWait(driver, Duration.ofSeconds(10));
  //     alertAwaiter.until(ExpectedConditions.alertIsPresent());
     /*  
      Wait<WebDriver> mWait = new FluentWait<WebDriver>(driver)
      		.withTimeout(Duration.ofSeconds(10))
      		.pollingEvery(Duration.ofSeconds(2))
      		.ignoring(NoSuchElementException.class);
      
      WebElement message = mWait.until(new Function<WebDriver,WebElement>(){
      	public WebElement apply(WebDriver driver) {
      		return driver.findElement(By.className("alert"));
      	}
      });
*/
      
