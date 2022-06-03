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

import io.github.bonigarcia.wdm.managers.ChromeDriverManager;

public class RoutineTestIT {
	public static WebDriver driver=null;

	public RoutineTestIT() {}
	
	@Before
    public void initializeDriver() throws MalformedURLException {
		ChromeDriverManager.getInstance().setup();
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized");
		
        driver = new ChromeDriver(options);  
        
        userLogged();
	}
    
    @After
    public void finishDriver() {
        driver.close();
    }

    public void userLogged() {
        driver.get("http://localhost:4200/#/login");
      
		driver.findElement(By.id("mat-input-0")).sendKeys("jmdicostanzo11@gmail.com"); 
		driver.findElement(By.id("mat-input-1")).sendKeys("1234");
		
		WebElement login = driver.findElement(By.className("btn"));
        login.click(); 
    }
    
    @Test
    public void whenIEnterAllTheValidDataThenTheStartRoutineButtonIsEnabled() throws Exception {      
        driver.getCurrentUrl();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        
        driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);

    	//El usuario clikea en el link Actividades Usuario
        WebElement activityUserLink = driver.findElement(By.xpath("//*[contains(@id,'activityUser')]"));
        activityUserLink.click();        
        Thread.sleep(2000);        

    	//El usuario elige generar una rutina por nivel
    	WebElement routineLink = driver.findElement(By.xpath("//*[contains(@id,'routineByLevel')]"));
    	routineLink.click();
    	
    	//El usuario clickea para ver las rutinas del nivel deseado
    	WebElement watchRoutines = driver.findElement(By.className("btn-warning"));
    	watchRoutines.click();

        driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);

    	//El usuario elige la rutina deseada
        WebElement routine = driver.findElement(By.xpath("//a[contains(text(),' Rutina Espartana ')]"));
        
        //EL usuario hace scroll
        js.executeScript("arguments[0].scrollIntoView();", routine);
        Thread.sleep(1000);        

        routine.click();      

        //El usuario ingresa la cantidad de semanas 
        WebElement weeksRoutine = driver.findElement(By.name("weeksRoutine"));
        weeksRoutine.sendKeys("4"); 

        //El usuario clickea en el checkbox para elegir los días
        WebElement checkBox = driver.findElement(By.className("mat-select-arrow"));
        checkBox.click();
    	        
        //El usuario selecciona los días de la rutina
        WebElement daySelect1 = driver.findElement(By.xpath("//*[contains(@id,'mat-option-0')]"));
        daySelect1.click();
        
        WebElement daySelect2 = driver.findElement(By.xpath("//*[contains(@id,'mat-option-2')]"));
        daySelect2.click();
        
        WebElement daySelect3 = driver.findElement(By.xpath("//*[contains(@id,'mat-option-4')]"));
        daySelect3.click();
        
        //Se Testea que el boton iniciar rutina este habilitado
        boolean value = driver.findElement(By.xpath("//button[contains(text(),'Iniciar Rutina')]")).isEnabled();

        Assert.assertEquals(value, true);      
    }
    
    @Test
    public void whenDaysOfTheWeekAreNotEnteredTheRoutineStartButtonIsDisabled() throws Exception {      
        driver.getCurrentUrl();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        
        driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);

    	//El usuario clikea en el link Actividades Usuario
        WebElement activityUserLink = driver.findElement(By.xpath("//*[contains(@id,'activityUser')]"));
        activityUserLink.click();        
        Thread.sleep(2000);        

    	//El usuario elige generar una rutina por nivel
    	WebElement routineLink = driver.findElement(By.xpath("//*[contains(@id,'routineByLevel')]"));
    	routineLink.click();
    	
    	//El usuario clickea para ver las rutinas del nivel deseado
    	WebElement watchRoutines = driver.findElement(By.className("btn-warning"));
    	watchRoutines.click();

        driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);

    	//El usuario elige la rutina deseada
        WebElement routine = driver.findElement(By.xpath("//a[contains(text(),' Rutina Espartana ')]"));
        
        //EL usuario hace scroll
        js.executeScript("arguments[0].scrollIntoView();", routine);
        Thread.sleep(1000);        

        routine.click();      

        //El usuario ingresa la cantidad de semanas 
        WebElement weeksRoutine = driver.findElement(By.name("weeksRoutine"));
        weeksRoutine.sendKeys("4"); 

        //Se Testea que el boton iniciar rutina este deshabilitado
        boolean value = driver.findElement(By.xpath("//button[contains(text(),'Iniciar Rutina')]")).isEnabled();

        Assert.assertEquals(value, false);
    }
    
    @Test
    public void whenTheNumberOfWeeksIsNotEnteredTheRoutineStartButtonIsDisabled() throws Exception {      
        driver.getCurrentUrl();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        
        driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);

    	//El usuario clikea en el link Actividades Usuario
        WebElement activityUserLink = driver.findElement(By.xpath("//*[contains(@id,'activityUser')]"));
        activityUserLink.click();        
        Thread.sleep(2000);        

    	//El usuario elige generar una rutina por nivel
    	WebElement routineLink = driver.findElement(By.xpath("//*[contains(@id,'routineByLevel')]"));
    	routineLink.click();
    	
    	//El usuario clickea para ver las rutinas del nivel deseado
    	WebElement watchRoutines = driver.findElement(By.className("btn-warning"));
    	watchRoutines.click();

        driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);

    	//El usuario elige la rutina deseada
        WebElement routine = driver.findElement(By.xpath("//a[contains(text(),' Rutina Espartana ')]"));
        
        //EL usuario hace scroll
        js.executeScript("arguments[0].scrollIntoView();", routine);
        Thread.sleep(1000);        

        routine.click();      

        //El usuario clickea en el checkbox para elegir los días
        WebElement checkBox = driver.findElement(By.className("mat-select-arrow"));
        checkBox.click();
    	        
        //El usuario selecciona los días de la rutina
        WebElement daySelect1 = driver.findElement(By.xpath("//*[contains(@id,'mat-option-0')]"));
        daySelect1.click();
        
        WebElement daySelect2 = driver.findElement(By.xpath("//*[contains(@id,'mat-option-2')]"));
        daySelect2.click();
        
        WebElement daySelect3 = driver.findElement(By.xpath("//*[contains(@id,'mat-option-4')]"));
        daySelect3.click();
        
        //Se Testea que el boton iniciar rutina este deshabilitado
        boolean value = driver.findElement(By.xpath("//button[contains(text(),'Iniciar Rutina')]")).isEnabled();

        Assert.assertEquals(value, false);
    }
    
    @Test
    public void WhenIEnterAllTheCorrectDataAndClickOnTheStartRoutineButtonTheRoutineIsGeneratedCorrectly() throws Exception {      
        driver.getCurrentUrl();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        
        driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);

    	//El usuario clikea en el link Actividades Usuario
        WebElement activityUserLink = driver.findElement(By.xpath("//*[contains(@id,'activityUser')]"));
        activityUserLink.click();        
        Thread.sleep(2000);        

    	//El usuario elige generar una rutina por nivel
    	WebElement routineLink = driver.findElement(By.xpath("//*[contains(@id,'routineByLevel')]"));
    	routineLink.click();
    	
    	//El usuario clickea para ver las rutinas del nivel deseado
    	WebElement watchRoutines = driver.findElement(By.className("btn-warning"));
    	watchRoutines.click();

        driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);

    	//El usuario elige la rutina deseada
        WebElement routine = driver.findElement(By.xpath("//a[contains(text(),' Rutina Espartana ')]"));
        
        //EL usuario hace scroll
        js.executeScript("arguments[0].scrollIntoView();", routine);
        Thread.sleep(1000);        

        routine.click();      

        //El usuario ingresa la cantidad de semanas 
        WebElement weeksRoutine = driver.findElement(By.name("weeksRoutine"));
        weeksRoutine.sendKeys("4"); 

        //El usuario clickea en el checkbox para elegir los días
        WebElement checkBox = driver.findElement(By.className("mat-select-arrow"));
        checkBox.click();
    	        
        //El usuario selecciona los días de la rutina
        WebElement daySelect1 = driver.findElement(By.xpath("//*[contains(@id,'mat-option-0')]"));
        daySelect1.click();
        
        WebElement daySelect2 = driver.findElement(By.xpath("//*[contains(@id,'mat-option-2')]"));
        daySelect2.click();
        
        WebElement daySelect3 = driver.findElement(By.xpath("//*[contains(@id,'mat-option-4')]"));
        daySelect3.click();

        //Se clickea en el boton iniciar rutina 
        WebElement startRoutine = driver.findElement(By.id("startRoutine"));  
        js.executeScript("arguments[0].click();", startRoutine);

        Thread.sleep(3000);        

        //Se testea que aparezca el modal con el texto que se genero correctamente la rutina 
        WebElement message = driver.findElement(By.xpath("//h4[contains(text(),'Su rutina se generó correctamente!')]"));
        String messageExpCurrent = message.getText();
        String messageExp = "SU RUTINA SE GENERÓ CORRECTAMENTE!";

        Assert.assertEquals(messageExp, messageExpCurrent); 
    }
    
    @Test
    public void whenIGenerateARoutineCorrectlyThenClickButtonViewMyRoutinesRedirectToMyRoutinesPage() throws Exception {      
        driver.getCurrentUrl();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        
        driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);

    	//El usuario clikea en el link Actividades Usuario
        WebElement activityUserLink = driver.findElement(By.xpath("//*[contains(@id,'activityUser')]"));
        activityUserLink.click();        
        Thread.sleep(2000);        

    	//El usuario elige generar una rutina por nivel
    	WebElement routineLink = driver.findElement(By.xpath("//*[contains(@id,'routineByLevel')]"));
    	routineLink.click();
    	
    	//El usuario clickea para ver las rutinas del nivel deseado
    	WebElement watchRoutines = driver.findElement(By.className("btn-warning"));
    	watchRoutines.click();

        driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);

    	//El usuario elige la rutina deseada
        WebElement routine = driver.findElement(By.xpath("//a[contains(text(),' Rutina Espartana ')]"));
        
        //EL usuario hace scroll
        js.executeScript("arguments[0].scrollIntoView();", routine);
        Thread.sleep(1000);        

        routine.click();      

        //El usuario ingresa la cantidad de semanas 
        WebElement weeksRoutine = driver.findElement(By.name("weeksRoutine"));
        weeksRoutine.sendKeys("4"); 

        //El usuario clickea en el checkbox para elegir los días
        WebElement checkBox = driver.findElement(By.className("mat-select-arrow"));
        checkBox.click();
    	        
        //El usuario selecciona los días de la rutina
        WebElement daySelect1 = driver.findElement(By.xpath("//*[contains(@id,'mat-option-0')]"));
        daySelect1.click();
        
        WebElement daySelect2 = driver.findElement(By.xpath("//*[contains(@id,'mat-option-2')]"));
        daySelect2.click();
        
        WebElement daySelect3 = driver.findElement(By.xpath("//*[contains(@id,'mat-option-4')]"));
        daySelect3.click();

        //Se clickea en el boton iniciar rutina 
        WebElement startRoutine = driver.findElement(By.id("startRoutine"));  
        js.executeScript("arguments[0].click();", startRoutine);

        Thread.sleep(3000);               
        
        //Se testea cuando se clikea en el botón de ver mis rutinas te redirije a la page de mis rutinas
        WebElement lookRoutine = driver.findElement(By.xpath("//button[contains(text(),'Ver mis rutinas')]"));
        lookRoutine.click();        

        String currentUrl="http://localhost:4200/#/myRoutine";
        String expectedUrl= driver.getCurrentUrl();
        
        Assert.assertEquals(expectedUrl,currentUrl);  
    }
}
