package com.calisapp;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.calisapp.model.Exercise;

class ExerciseTest {

	static Exercise ejercicio1;
	
	@BeforeAll
	static void setUp() {
		
		ejercicio1 = new Exercise.ExerciseBuilder()
							.withRepetitions(12)
							.withSeries(3)
							.withLevelExcercise("Principiante")
							.withExerciseTime(60)
							.withBreakTime(2)
							.withGeneratedBy("USER")
							.build();
	}
	
	@Test
	public void generacionDeExcerciseConSusVariablesTest(){
		assertEquals(ejercicio1.getRepetitions(), 12);
		assertEquals(ejercicio1.getSeries(), 3);
		assertEquals(ejercicio1.getLevelExcercise(), "Principiante");
		assertEquals(ejercicio1.getExerciseTime(), 60);
		assertEquals(ejercicio1.getBreakTime(), 2);
		assertEquals(ejercicio1.getGeneratedBy(), "USER");
	}
}
