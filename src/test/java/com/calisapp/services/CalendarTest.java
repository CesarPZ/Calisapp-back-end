package com.calisapp.services;

import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Calendar;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import com.calisapp.CalisappApplication;
import com.calisapp.daos.DayRoutineDAO;
import com.calisapp.model.CalendarUser;
import com.calisapp.model.Exercise;
import com.calisapp.model.ProcessExercise;
import com.calisapp.model.Routine;
import com.calisapp.model.RoutineByLevel;
import com.calisapp.repositories.CalendarUserRepository;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes=CalisappApplication.class)
@ImportResource("applicationContext.xml")
public class CalendarTest {
	
    @Autowired
    private CalendarUserService calendarService;
    
    @MockBean
    private CalendarUserRepository calendarUserRepository;
    
    private CalendarUser calendarRutinaDe1Dia3VecesALaSemana() {
		ProcessExercise processExercise1 = new ProcessExercise.ProcessExerciseBuilder().build();
		ProcessExercise processExercise2  = new ProcessExercise.ProcessExerciseBuilder().build();
		ProcessExercise processExercise3  = new ProcessExercise.ProcessExerciseBuilder().build();
		
		Exercise exercise1 = new Exercise.ExerciseBuilder()
				.withProcessExercise(processExercise1)
				.withDayExercise(1)
				.build();
		
		Exercise exercise2 = new Exercise.ExerciseBuilder()
				.withProcessExercise(processExercise2)
				.withDayExercise(1)
				.build();
		
		Exercise exercise3 = new Exercise.ExerciseBuilder()
				.withProcessExercise(processExercise3)
				.withDayExercise(1)
				.build();
		
		Set<Exercise> ejercicios1 = new HashSet<Exercise>();
		ejercicios1.add(exercise1);
		ejercicios1.add(exercise2);
		ejercicios1.add(exercise3);
		
		Routine rutina1 = new RoutineByLevel.RoutineByLevelBuilder()
				.withNameRoutine("Rutina para Principiantes")
				.withExercises(ejercicios1).build();
		
		List<Integer> diasLunMiercVier = new ArrayList<Integer>();
		diasLunMiercVier.add(1);
		diasLunMiercVier.add(3);
		diasLunMiercVier.add(5);
		
		return new CalendarUser.CalendarUserBuilder()
				.withRoutine(rutina1)
				.withDaysRoutine(diasLunMiercVier)
				.withWeeksRoutine(2)
				.build();
    }
    
    private CalendarUser calendarRutinaDe2Dia2VecesALaSemana() {
		ProcessExercise processExercise1 = new ProcessExercise.ProcessExerciseBuilder().build();
		ProcessExercise processExercise2  = new ProcessExercise.ProcessExerciseBuilder().build();
		ProcessExercise processExercise3  = new ProcessExercise.ProcessExerciseBuilder().build();
		
		Exercise exercise1 = new Exercise.ExerciseBuilder()
				.withProcessExercise(processExercise1)
				.withDayExercise(1)
				.build();
		
		Exercise exercise2 = new Exercise.ExerciseBuilder()
				.withProcessExercise(processExercise2)
				.withDayExercise(1)
				.build();
		
		Exercise exercise3 = new Exercise.ExerciseBuilder()
				.withProcessExercise(processExercise3)
				.withDayExercise(2)
				.build();
		
		Set<Exercise> ejercicios1 = new HashSet<Exercise>();
		ejercicios1.add(exercise1);
		ejercicios1.add(exercise2);
		ejercicios1.add(exercise3);
		
		Routine rutina1 = new RoutineByLevel.RoutineByLevelBuilder()
				.withNameRoutine("Rutina Avanzada de 2 dias")
				.withExercises(ejercicios1).build();
		
		List<Integer> diasMartYDom = new ArrayList<Integer>();
		diasMartYDom.add(2);
		diasMartYDom.add(7);
		
		return new CalendarUser.CalendarUserBuilder()
				.withRoutine(rutina1)
				.withDaysRoutine(diasMartYDom)
				.withWeeksRoutine(4)
				.build();
    }
    
    @Test
    public void calcularUnaRutinaDe1Dia2SemanasTest() {
    	List<CalendarUser> calendarUser = new ArrayList<CalendarUser>();
    	calendarUser.add(calendarRutinaDe1Dia3VecesALaSemana());
        when(calendarUserRepository.findWithUserId(1L)).thenReturn(calendarUser);
      
        List<DayRoutineDAO> calculoCalendar = calendarService.findWithUserId(1L);
        Integer rutinasDiasLunes = 0;
        Integer rutinasDiasSabados = 0;
        for(DayRoutineDAO calendar: calculoCalendar) {
        	Calendar c = Calendar.getInstance();
        	c.setTime(calendar.getDayRoutine());
        	Integer dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
        	if(dayOfWeek == 2) {
        		rutinasDiasLunes ++;
        	}
        	if(dayOfWeek == 7) {
        		rutinasDiasSabados ++;
        	}
        }
        
        assertEquals(calculoCalendar.size(), 6, "Se Espera que se generen 6 DayRoutineDAO debido el user solo tiene"
        									  + "una rutina de 2 semanas, para los dias Lunes miercoles y viernes");
        
        assertEquals(rutinasDiasLunes, 2, "Se Espera existan 2 rutinas los dias lunes porque son 2 semanas");        
        assertEquals(rutinasDiasSabados, 0, "Se Espera existan 0 rutinas los dias sabados porque este dia no esta asignado");
        for(DayRoutineDAO calendar: calculoCalendar) {
        	assertEquals(calendar.getExercises().size(), 3, "Se Espera que todos los dias tengan la misma cantidad de ejercicios");
        	assertEquals(calendar.getRoutineName(), "Rutina para Principiantes", "Se Espera que todos calendarios tengan el mismo nombre de rutina");
        }
    }

    @Test
    public void calcularUnaRutinaDe2Dias4SemanasTest() {
    	List<CalendarUser> calendarUser = new ArrayList<CalendarUser>();
    	calendarUser.add(calendarRutinaDe2Dia2VecesALaSemana());
        when(calendarUserRepository.findWithUserId(1L)).thenReturn(calendarUser);
      
        List<DayRoutineDAO> calculoCalendar = calendarService.findWithUserId(1L);
 
        List<DayRoutineDAO> rutinaMartes = new ArrayList<DayRoutineDAO>();
        List<DayRoutineDAO> rutinaDomingo = new ArrayList<DayRoutineDAO>();
        
        for(DayRoutineDAO calendar: calculoCalendar) {
    	    Calendar c = Calendar.getInstance();
        	c.setTime(calendar.getDayRoutine());
        	Integer dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
        	if(dayOfWeek == 3) { //Martes
        		rutinaMartes.add(calendar);
        	}
        	if(dayOfWeek == 1) { //Domingo
        		rutinaDomingo.add(calendar);
        	}
        }
        
        assertEquals(calculoCalendar.size(), 8, "Se Espera que se generen 8 DayRoutineDAO debido el user solo tiene"
        									  + "una rutina de 2 semanas, para los dias Martes y Domingos");
        assertEquals(rutinaMartes.size(), 4, "Se Espera que se generen 4 DayRoutineDAO los dias martes");
        assertEquals(rutinaDomingo.size(), 4, "Se Espera que se generen 4 DayRoutineDAO los dias domingos");
        
        //Se espera que los ejercicios de los domingos sean distintos a lo de los martes
        assertNotEquals(rutinaDomingo.get(0).getExercises(), rutinaMartes.get(0).getExercises());												
        
        for(DayRoutineDAO calendar: calculoCalendar) {
    	  	assertEquals(calendar.getRoutineName(), "Rutina Avanzada de 2 dias", "Se Espera que todos calendarios tengan el mismo nombre de rutina");
        }
    }
}
