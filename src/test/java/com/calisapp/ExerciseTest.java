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
							.withNameExercise("Dominadas")
							.withDescription("Levantar el cuerpo mientras este pende de una barra de dominadas")
							.withComplexityNumber(3)
							.withMuscle("Espalda")
							.withUrlVideo("https://Calisapp/exercise/dominada")
							.build();
	}
	
	@Test
	public void generacionDeExcerciseConSusVariables(){
		assertEquals(ejercicio1.getnameExercise(), "Dominadas");
		assertEquals(ejercicio1.getDescription(),
				"Levantar el cuerpo mientras este pende de una barra de dominadas");
		assertEquals(ejercicio1.getComplexityNumber(), 3);
		assertEquals(ejercicio1.getMuscle(), "Espalda");
		assertEquals(ejercicio1.getUrlVideo(), "https://Calisapp/exercise/dominada");
	}
	
	
}
