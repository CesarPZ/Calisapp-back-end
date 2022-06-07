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

public class RoutineTestIT {
	public static WebDriver driver=null;
	public static By starRoutine = By.xpath("//button[contains(text(),'Iniciar Rutina')]");
	public static By messageAlert = By.xpath("//h4[contains(text(),'Su rutina se generó correctamente!')]");
	
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
      
        setInputData("jmdicostanzo11@gmail.com", "1234");
		
		WebElement login = driver.findElement(By.className("btn"));
        login.click(); 
    }
    
    public void setInputData (String mail, String password) {
    	driver.findElement(By.id("mat-input-0")).sendKeys(mail); 
		driver.findElement(By.id("mat-input-1")).sendKeys(password);
    }
    
    public void selectRoutine() {
        JavascriptExecutor js = (JavascriptExecutor) driver;

    	//El usuario clikea en el link Actividades Usuario
        WebElement activityUserLink = new WebDriverWait(driver, 10)
    			.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(@id,'activityUser')]"))); 
        activityUserLink.click();        

    	//El usuario elige generar una rutina por nivel
        WebElement routineLink = new WebDriverWait(driver, 10)
    			.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(@id,'routineByLevel')]"))); 
        routineLink.click();
    	
    	//El usuario clickea para ver las rutinas del nivel deseado
    	WebElement watchRoutines = driver.findElement(By.className("btn-warning"));
    	watchRoutines.click();

    	//El usuario elige la rutina deseada
        WebElement routine = new WebDriverWait(driver, 10)
    			.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),' Rutina Espartana ')]"))); 
        
        //EL usuario hace scroll
        js.executeScript("arguments[0].scrollIntoView();", routine);
        routine.click();    
    }
    
    public void setInputDataTheroutine() {
    	numberOfWeeks();
    	selectTheDaysOfTheRoutine();
    }
    
    public void numberOfWeeks() {
    	//El usuario ingresa la cantidad de semanas 
        WebElement weeksRoutine = driver.findElement(By.name("weeksRoutine"));
        weeksRoutine.sendKeys("4"); 
    }
    
    public void selectTheDaysOfTheRoutine() {
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
    }
    
    public void clickStarRoutine() {
    	 JavascriptExecutor js = (JavascriptExecutor) driver;

         //Se clickea en el boton iniciar rutina         
         WebElement startRoutine = new WebDriverWait(driver, 10)
     			.until(ExpectedConditions.elementToBeClickable(By.id("startRoutine"))); 
         js.executeScript("arguments[0].click();", startRoutine);
    }
    
    @Test
    public void whenIEnterAllTheValidDataThenTheStartRoutineButtonIsEnabled() throws Exception {      
        driver.getCurrentUrl();
                  
        selectRoutine();
        setInputDataTheroutine();
        
        boolean value = driver.findElement(starRoutine).isEnabled();

        assertEquals(value, true);      
    }
    
    @Test
    public void whenDaysOfTheWeekAreNotEnteredTheRoutineStartButtonIsDisabled() throws Exception {      
    	driver.getCurrentUrl();
    	
    	selectRoutine();
    	numberOfWeeks();

        boolean value = driver.findElement(starRoutine).isEnabled();

        assertEquals(value, false);
    }
    
    @Test
    public void whenTheNumberOfWeeksIsNotEnteredTheRoutineStartButtonIsDisabled() throws Exception {      
        driver.getCurrentUrl();
        
        selectRoutine();    
        selectTheDaysOfTheRoutine();
        
        boolean value = driver.findElement(starRoutine).isEnabled();

        assertEquals(value, false);
    }
   
    @Test
    public void WhenIEnterAllTheCorrectDataAndClickOnTheStartRoutineButtonTheRoutineIsGeneratedCorrectly() throws Exception {      
        driver.getCurrentUrl();

        selectRoutine();    
        setInputDataTheroutine();
        clickStarRoutine();
  
        WebElement alert = new WebDriverWait(driver, 10)
        		.until(ExpectedConditions.visibilityOfElementLocated(messageAlert));
     
        String messageExpCurrent = alert.getText();
        String messageExp = "SU RUTINA SE GENERÓ CORRECTAMENTE!";

        assertEquals(messageExp, messageExpCurrent); 
    }
    
    @Test
    public void whenIGenerateARoutineCorrectlyThenClickButtonViewMyRoutinesRedirectToMyRoutinesPage() throws Exception {      
    	driver.getCurrentUrl();
        JavascriptExecutor js = (JavascriptExecutor) driver;

        selectRoutine();    
        setInputDataTheroutine();
        clickStarRoutine();
        
        WebElement lookRoutine = new WebDriverWait(driver, 10)
    			.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Ver mis rutinas')]"))); 
        js.executeScript("arguments[0].click();", lookRoutine);

        String currentUrl="http://localhost:4200/#/myRoutine";
        String expectedUrl= driver.getCurrentUrl();
        
        assertEquals(expectedUrl,currentUrl);  
    }
}
