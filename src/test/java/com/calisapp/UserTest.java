package com.calisapp;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.calisapp.model.Exercise;
import com.calisapp.model.Routine;
import com.calisapp.model.RoutineOfUser;
import com.calisapp.model.User;

class UserTest {

	static User user1;
	static User user2;
	static Routine routine1;
	static Routine routine2;
	static Routine routine3;
	static Exercise ejercicio1;
	static Exercise ejercicio2;
	
	@BeforeAll
	static void setUp() {
		routine1 = new RoutineOfUser.RoutineOfUserBuilder().build();
		routine2 = new RoutineOfUser.RoutineOfUserBuilder().build();
		routine3 = new RoutineOfUser.RoutineOfUserBuilder().build();
		
		List<Routine> rutinas = new ArrayList<Routine>();
		rutinas.add(routine1);
		rutinas.add(routine2);
		rutinas.add(routine3);
		
		user1 = new User.UserBuilder()
					.withName("Cesar")
					.withMail("cesar@gmail.com")
					.withPassword("cesar123")
					.withRoutines(rutinas)
					.build();
		
		user2 = new User.UserBuilder()
				.withName("Marcelo")
				.withMail("marcelo@gmail.com")
				.withPassword("cesar123")
				.build();
	}
	
	@Test
	public void generacionDeUserConSusVariablesTest(){
		assertEquals(user1.getName(), "Cesar");
		assertEquals(user1.getMail(), "cesar@gmail.com");
		assertEquals(user1.getPassword(), "cesar123");
		assertEquals(user1.getRoutines().size(), 3);
	}
	
	@Test
	public void generateRoutineTest(){
		ejercicio1 = new Exercise.ExerciseBuilder().build();
		ejercicio2 = new Exercise.ExerciseBuilder().build();
		
		Set<Exercise> ejerciciosRoutine = new HashSet<Exercise>();
		ejerciciosRoutine.add(ejercicio1);
		ejerciciosRoutine.add(ejercicio2);
		
		Routine newRoutine = user2.generateRoutine("nuevaRutina", ejerciciosRoutine);
		 
		assertEquals(user2.getRoutines().size(), 1);
		assertEquals(newRoutine.getNameRoutine(), "nuevaRutina");
		assertEquals(newRoutine.getExercises().size(), 2);
	}
	
	@Test
	public void generateCustomRoutineTest(){
        User user = new User("alex", "alex@gmail.com", "1234");

		ejercicio1 = new Exercise.ExerciseBuilder().build();
		ejercicio2 = new Exercise.ExerciseBuilder().build();
		
		Set<Exercise> ejerciciosRoutine = new HashSet<Exercise>();
		ejerciciosRoutine.add(ejercicio1);
		ejerciciosRoutine.add(ejercicio2);
		
		Routine newRoutine = user.generateRoutine("miRutina", ejerciciosRoutine);

		assertEquals(user.getRoutines().size(), 1);
		assertEquals(newRoutine.getNameRoutine(), "miRutina");
		assertEquals(newRoutine.getExercises().size(), 2);
	}
	
	/*
	@Test
	public void addEventTocalendarTest(){
		//Dia 5 equivale a viernes, y 4 repeticiones
		List<CalendarUser> calendarToUser = user2.addEventTocalendar(5, 4, routine1);
		
		assertEquals(user2.getCalendar().size(), 4);
		assertEquals(user2.getCalendar(), calendarToUser);
		
		for (CalendarUser calendar : calendarToUser) {
//			LocalDate dateCalendar = LocalDate.ofInstant(calendar.getDateRoutine().toInstant(), ZoneId.systemDefault());
//			assertEquals(dateCalendar.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH), "Friday");
		}
	}*/
}