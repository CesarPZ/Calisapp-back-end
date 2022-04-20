package com.calisapp;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.calisapp.model.Exercise;
import com.calisapp.model.RoutineByLevel;
import com.calisapp.model.RoutineOfUser;

class RoutineTest {

	static RoutineByLevel routineByLevel;
	static RoutineOfUser routineOfUser;
	
	static Exercise ejercicio1;
	static Exercise ejercicio2;
	static Exercise ejercicio3;
	static Exercise ejercicio4;
	static Exercise ejercicio5;
	
	@BeforeAll
	static void setUp() {
		ejercicio1 = new Exercise.ExerciseBuilder().build();
		ejercicio2 = new Exercise.ExerciseBuilder().build();
		ejercicio3 = new Exercise.ExerciseBuilder().build();
		ejercicio4 = new Exercise.ExerciseBuilder().build();
		ejercicio5 = new Exercise.ExerciseBuilder().build();
		
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
							.build();
		
		routineOfUser = new RoutineOfUser.RoutineOfUserBuilder()
							.withNameRoutine("Intermedio")
							.withExercises(ejerciciosRoutineOfUser)
							.build();
	}
	
	@Test
	public void generacionDeRoutineByLevel(){
		assertEquals(routineByLevel.getGeneratedBy(), "APP");
		assertEquals(routineByLevel.getExercises().size(), 2);
	}
	
	@Test
	public void generacionDeRoutineOfUser(){
		assertEquals(routineOfUser.getGeneratedBy(), "USER");
		assertEquals(routineOfUser.getExercises().size(), 3);
	}

}
