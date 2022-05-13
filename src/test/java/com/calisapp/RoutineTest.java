package com.calisapp;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.calisapp.model.Exercise;
import com.calisapp.model.RoutineByLevel;
import com.calisapp.model.RoutineOfUser;
import com.calisapp.model.User;

class RoutineTest {
	static RoutineByLevel routineByLevel;
	static RoutineOfUser routineOfUser;
	
	static Exercise ejercicio1;
	static Exercise ejercicio2;
	static Exercise ejercicio3;
	static Exercise ejercicio4;
	static Exercise ejercicio5;
	
	static User usuario1;
	
	@BeforeAll
	static void setUp() {
		ejercicio1 = new Exercise.ExerciseBuilder().build();
		ejercicio2 = new Exercise.ExerciseBuilder().build();
		ejercicio3 = new Exercise.ExerciseBuilder().build();
		ejercicio4 = new Exercise.ExerciseBuilder().build();
		ejercicio5 = new Exercise.ExerciseBuilder().build();
		
		usuario1 = new User("Pepito", "Pepito@gmail", "1234");
		
		Set<Exercise> ejerciciosRoutineByLevel = new HashSet<Exercise>();
		ejerciciosRoutineByLevel.add(ejercicio1);
		ejerciciosRoutineByLevel.add(ejercicio2);
		
		Set<Exercise> ejerciciosRoutineOfUser = new HashSet<Exercise>();
		ejerciciosRoutineOfUser.add(ejercicio3);
		ejerciciosRoutineOfUser.add(ejercicio4);
		ejerciciosRoutineOfUser.add(ejercicio5);
		
		routineByLevel = new RoutineByLevel.RoutineByLevelBuilder()
							.withNameRoutine("Avanzado")
							.withExercises(ejerciciosRoutineByLevel)
							.withLevel("Avanzado")
							.build();
		
		routineOfUser = new RoutineOfUser.RoutineOfUserBuilder()
							.withNameRoutine("Intermedio")
							.withExercises(ejerciciosRoutineOfUser)
							.build();
	}
	
	@Test
	public void generationOfRoutineByLevelTest(){
		assertEquals(routineByLevel.getGeneratedBy(), "APP");
		assertEquals(routineByLevel.getExercises().size(), 2);
		assertEquals(routineByLevel.getLevel(), "Avanzado");
	}
	
	@Test
	public void generationOfRoutineOfUSerTest(){
		Set<Exercise> exerciseUser = new HashSet<Exercise>();
		exerciseUser.add(ejercicio2);
		exerciseUser.add(ejercicio5);
		
		RoutineOfUser rutinaUser = new RoutineOfUser ("rutina picante", exerciseUser);
		
		assertEquals(rutinaUser.getGeneratedBy(), "USER");
		assertEquals(rutinaUser.getExercises().size(), 2);
		assertEquals(rutinaUser.getNameRoutine(), "rutina picante");
	}
	
	@Test
	public void editRoutineOfUserTest(){
		Set<Exercise> newExercises = new HashSet<Exercise>();
		newExercises.add(ejercicio2);
		newExercises.add(ejercicio5);
		
		routineOfUser.updateRoutine("Rutina Modificada", newExercises);
		
		assertEquals(routineOfUser.getNameRoutine(), "Rutina Modificada");
		assertEquals(routineOfUser.getExercises().size(), 2);
	}
	
	@Test
	public void editRoutineOfUserSinNameNiEjerciciosTest(){
		routineOfUser.updateRoutine(null, null);
		
		assertEquals(routineOfUser.getNameRoutine(), "Intermedio");
		assertEquals(routineOfUser.getExercises().size(), 3);
	}

	@Test
	public void removeAnExerciseFromARoutineTest(){
		RoutineByLevel routine = new RoutineByLevel ("rutina 1", "APP");
		routine.addExercise(ejercicio2);
		routine.addExercise(ejercicio5);
		
		assertEquals(routine.getExercises().size(), 2);
		
		routine.removeExercise(ejercicio5);
		assertEquals(routine.getExercises().size(), 1);
	}
}
