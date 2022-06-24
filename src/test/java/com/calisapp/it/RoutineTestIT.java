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

import io.github.bonigarcia.wdm.managers.ChromeDriverManager;

public class RoutineTestIT {
	public static WebDriver driver = null;
	public static By starRoutine = By.xpath("//button[contains(text(),'Iniciar Rutina')]");
	public static By messageAlert = By.xpath("//h2[contains(text(),'Su rutina se generó correctamente!')]");
	public static By advancedRoutine = By.className("btn-warning");
	public static By routineEspartana = By.xpath("//a[contains(text(),' Rutina Espartana ')]");
	public static By seeRoutineButton = By.xpath("//button[contains(text(),'Ver mis rutinas')]");
	public static By activityUserButton = By.id("activityUser");
	public static By routineByLevelLink = By.id("routineByLevel");
	public static By lunes = By.xpath("//*[contains(@id,'mat-option-0')]");
	public static By miercoles = By.xpath("//*[contains(@id,'mat-option-2')]");
	public static By viernes = By.xpath("//*[contains(@id,'mat-option-4')]");
	
	public RoutineTestIT() {}

	@Before
    public void initializeDriver() throws MalformedURLException {
		ChromeDriverManager.getInstance().setup();

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized");
		
        driver = new ChromeDriver(options);  
        
        TestUtils.userLogged("jmdicostanzo11@gmail.com", "1234", driver);  
	}
    
    @After
    public void finishDriver() {
        driver.close();
    }

    public void selectRoutineByLevel(By routineLevel, By selectedRoutine) {
    	//JavascriptExecutor js = (JavascriptExecutor) driver;
    	
    	//El usuario clikea en el link Actividades Usuario 
        WebElement activityUserLink = WaitElement.waitForElementToBeClickable(driver, 10, activityUserButton);
        activityUserLink.click();        
        
    	//El usuario elige generar una rutina por nivel    	
        WebElement routineLink = WaitElement.waitForElementToBeClickable(driver, 10, routineByLevelLink);
        routineLink.click();
        
    	//El usuario clickea para ver las rutinas del nivel deseado
    	WebElement watchRoutines = driver.findElement(routineLevel);
    	watchRoutines.click();

    	//El usuario elige la rutina deseada       
        WebElement routine = WaitElement.waitForElementToBeClickable(driver, 20, selectedRoutine);
        //js.executeScript("arguments[0].click();", routine);
        routine.click();
    }
    
    public void numberOfWeeks(Integer numberWeek) {
    	//El usuario ingresa la cantidad de semanas 
        WebElement weeksRoutine = driver.findElement(By.name("weeksRoutine"));
        weeksRoutine.sendKeys(numberWeek.toString()); 
    }
    
    public void clickSelect() {
    	//El usuario clickea en el checkbox para elegir los días
        WebElement checkBox = driver.findElement(By.className("mat-select-arrow"));
        checkBox.click();
    }
    
    public void selectTheDayOfTheRoutine(By locator) {
    	WebElement daySelect = driver.findElement(locator);
        daySelect.click();
    }
    
    public void clickStarRoutine() {
    	 JavascriptExecutor js = (JavascriptExecutor) driver;

         //Se clickea en el boton iniciar rutina         
         WebElement startRoutine = WaitElement.waitForElementToBeClickable(driver, 10, By.id("startRoutine"));
         js.executeScript("arguments[0].click();", startRoutine);
    }
    
    @Test
    public void whenIEnterAllTheValidDataThenTheStartRoutineButtonIsEnabled() throws Exception {      
        driver.getCurrentUrl();
                  
        selectRoutineByLevel(advancedRoutine, routineEspartana);
        numberOfWeeks(4);
    	clickSelect();
        selectTheDayOfTheRoutine(lunes);
        
        boolean value = driver.findElement(starRoutine).isEnabled();

        assertEquals(value, true);      
    }
    
    @Test
    public void whenDaysOfTheWeekAreNotEnteredTheRoutineStartButtonIsDisabled() throws Exception {      
    	driver.getCurrentUrl();
    	
    	selectRoutineByLevel(advancedRoutine, routineEspartana);
    	numberOfWeeks(4);

        boolean value = driver.findElement(starRoutine).isEnabled();

        assertEquals(value, false);
    }
    
    @Test
    public void whenTheNumberOfWeeksIsNotEnteredTheRoutineStartButtonIsDisabled() throws Exception {      
        driver.getCurrentUrl();
        
        selectRoutineByLevel(advancedRoutine, routineEspartana);
        clickSelect();
        selectTheDayOfTheRoutine(lunes);
        
        boolean value = driver.findElement(starRoutine).isEnabled();

        assertEquals(value, false);
    }
   
    @Test
    public void WhenIEnterAllTheCorrectDataAndClickOnTheStartRoutineButtonTheRoutineIsGeneratedCorrectly() throws Exception {      
        driver.getCurrentUrl();

        selectRoutineByLevel(advancedRoutine, routineEspartana);    
    	numberOfWeeks(4);
    	clickSelect();
        selectTheDayOfTheRoutine(lunes);
        selectTheDayOfTheRoutine(miercoles);
        selectTheDayOfTheRoutine(viernes);
        clickStarRoutine();
  
        WebElement alert = WaitElement.waitForVisibilityOfElementLocated(driver, 10, messageAlert);
        
        String messageExpCurrent = alert.getText();
        String messageExp = "SU RUTINA SE GENERÓ CORRECTAMENTE!";

        assertEquals(messageExp, messageExpCurrent); 
    }
    
    @Test
    public void whenIGenerateARoutineCorrectlyThenClickButtonViewMyRoutinesRedirectToMyRoutinesPage() throws Exception {      
    	JavascriptExecutor js = (JavascriptExecutor) driver;
    	driver.getCurrentUrl();

        selectRoutineByLevel(advancedRoutine, routineEspartana);    
        numberOfWeeks(4);
    	clickSelect();
        selectTheDayOfTheRoutine(lunes);
        selectTheDayOfTheRoutine(miercoles);
        clickStarRoutine();
        
        WebElement lookRoutine = WaitElement.waitForElementToBeClickable(driver, 10, seeRoutineButton);
        js.executeScript("arguments[0].click();", lookRoutine);
                
        String currentUrl= "http://localhost:4200/#/myRoutine";
        String expectedUrl= driver.getCurrentUrl();
        
        assertEquals(expectedUrl,currentUrl);  
    }
}
