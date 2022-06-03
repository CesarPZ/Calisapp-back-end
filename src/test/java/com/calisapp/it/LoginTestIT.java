package com.calisapp.it;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.calisapp.repositories.UserRepository;

import io.github.bonigarcia.wdm.managers.ChromeDriverManager;

public class LoginTestIT {
	public static WebDriver driver=null;

	public LoginTestIT() {}

	@MockBean
    private UserRepository userRepository;
	
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
    public void whenIEnterAValidEmailAndPasswordTheLoginButtonIsEnabled() throws Exception {      
    	driver.get("http://localhost:4200/#/login");
        
		driver.findElement(By.id("mat-input-0")).sendKeys("test@gmail.com");
		driver.findElement(By.id("mat-input-1")).sendKeys("123456");
        
        boolean value = driver.findElement(By.name("submitLogin")).isEnabled();

        Assert.assertEquals(value, true);
    }
    
    @Test
    public void whenIEnterAnInvalidMailTheLoginButtonIsDisabled() throws Exception { 
        driver.get("http://localhost:4200/#/login");
        
		driver.findElement(By.id("mat-input-0")).sendKeys("test");
		driver.findElement(By.id("mat-input-1")).sendKeys("123456");
        
        boolean value = driver.findElement(By.name("submitLogin")).isEnabled();

        Assert.assertEquals(value, false);
    }
    
    @Test
    public void whenIEnterAnInvalidPasswordTheLoginButtonIsDisabled() throws Exception { 
        driver.get("http://localhost:4200/#/login");
        
		driver.findElement(By.id("mat-input-0")).sendKeys("test@gmail.com");
		driver.findElement(By.id("mat-input-1")).sendKeys("123");
		
        boolean value = driver.findElement(By.name("submitLogin")).isEnabled();
        
        Assert.assertEquals(value, false);
    }

    @Test
    public void whenIEnterAllTheDataWellAndClickTheLoginButtonTheUserIsLogged() throws Exception { 
        driver.get("http://localhost:4200/#/login");
        
		driver.findElement(By.id("mat-input-0")).sendKeys("jmdicostanzo11@gmail.com"); //Dato correcto
		driver.findElement(By.id("mat-input-1")).sendKeys("1234"); //Dato correcto
		
		WebElement login = driver.findElement(By.name("submitLogin"));
        login.click();
        
        Thread.sleep(3000);        

        String currentUrl="http://localhost:4200/#/home";
        String expectedUrl= driver.getCurrentUrl();
        
        Assert.assertEquals(expectedUrl,currentUrl);
        
        WebElement message = driver.findElement(By.className("lead"));
        String messageExpCurrent = message.getText();
        String messageExp = "Guía de calistenia, para organizar y planificar tus entrenamientos.";

        Assert.assertEquals(messageExp, messageExpCurrent);  
    }
    
    @Test
    public void whenEnterIncorrectDataThenErrorMessage() throws Exception { 
        driver.get("http://localhost:4200/#/login");
        
        driver.findElement(By.id("mat-input-0")).sendKeys("jmdicostanzo11@gmail.com");
        driver.findElement(By.id("mat-input-1")).sendKeys("12345"); //Dato incorrecto
		 
		WebElement login = driver.findElement(By.name("submitLogin"));
        login.click();
        
        driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);

        WebElement message = driver.findElement(By.className("alert"));
        String messageExpCurrent = message.getText();
        String messageExp = "Ingresó mal los datos";

        Assert.assertEquals(messageExp, messageExpCurrent);  
    }
    
    @Test
    public void whenClickRegisterLinkRedirectToSignUpPage() throws Exception { 
    	driver.get("http://localhost:4200/#/login");
     
    	WebElement registerLink = driver.findElement(By.xpath("//a[contains(text(),'Regístrate')]"));
    	registerLink.click();        

        String currentUrl="http://localhost:4200/#/register";
        String expectedUrl= driver.getCurrentUrl();
        
        Assert.assertEquals(expectedUrl,currentUrl);     
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
        
        Assert.assertEquals(expectedUrl,currentUrl);     
    }
    
    @Test
    public void whenTheUserIsLoggedTheLogoutButtonIsVisible() throws Exception { 
        driver.get("http://localhost:4200/#/login");
        
		driver.findElement(By.id("mat-input-0")).sendKeys("jmdicostanzo11@gmail.com"); //Dato correcto
		driver.findElement(By.id("mat-input-1")).sendKeys("1234"); //Dato correcto
		
		WebElement login = driver.findElement(By.name("submitLogin"));
        login.click();
        
        Thread.sleep(3000);        

        driver.getCurrentUrl();
        
        //Se testea que el boton de salir es visible cuando el usuario esta logueado
        boolean logout = driver.findElement(By.id("logout")).isDisplayed();
        
        Assert.assertEquals(logout, true);
    }
    
    @Test
    public void whenTheUserNotIsLoggedTheLogoutButtonIsNotVisible() throws Exception { 
        driver.get("http://localhost:4200/#/home");
        
        Thread.sleep(3000);        

        //Se testea que el boton de salir no es visible cuando el usuario no esta logueado
        Boolean isPresent = driver.findElements(By.id("/logout")).size() > 0;

        Assert.assertEquals(isPresent, false);
    }
    
    @Test
    public void WhenTheUserLogoutTheLoginButtonIsVisible() throws Exception { 
        driver.get("http://localhost:4200/#/login");
        JavascriptExecutor js = (JavascriptExecutor) driver;

		driver.findElement(By.id("mat-input-0")).sendKeys("jmdicostanzo11@gmail.com"); //Dato correcto
		driver.findElement(By.id("mat-input-1")).sendKeys("1234"); //Dato correcto
		
		WebElement login = driver.findElement(By.name("submitLogin"));
        login.click();
        
        driver.getCurrentUrl();
        Thread.sleep(2000);        

        //Se testea salir de la app
        WebElement logout = driver.findElement(By.id("logout"));
        logout.click();

        WebElement outButton = driver.findElement(By.id("outButton"));
        js.executeScript("arguments[0].click();", outButton);

    	//Se testea que al salir el boton de ingresar se hace visible
    	boolean ingresarLink = driver.findElement(By.id("getInTo")).isDisplayed();
        Assert.assertEquals(ingresarLink, true);
        
        //El boton de salir desaparece
        Boolean isPresent = driver.findElements(By.id("logout")).size() > 0;
        Assert.assertEquals(isPresent, false);
    }
}
