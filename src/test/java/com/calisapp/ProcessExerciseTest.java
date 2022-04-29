package com.calisapp;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.calisapp.model.ProcessExercise;

class ProcessExerciseTest {

	static ProcessExercise ejercicio1;
	
	@BeforeAll
	static void setUp() {
		
		ejercicio1 = new ProcessExercise.ProcessExerciseBuilder()
							.withNameExercise("Dominadas")
							.withDescription("Levantar el cuerpo mientras este pende de una barra de dominadas")
							.withUrlVideo("https://Calisapp/exercise/dominada")
							.withMainMuscle("Espalda")
							.withComplexityNumber(3)
							.build();
	}
	
	@Test
	public void generacionDeProcessExcerciseConSusVariablesTest(){
		assertEquals(ejercicio1.getNameExercise(), "Dominadas");
		assertEquals(ejercicio1.getDescription(),
				"Levantar el cuerpo mientras este pende de una barra de dominadas");
		assertEquals(ejercicio1.getUrlVideo(), "https://Calisapp/exercise/dominada");
		assertEquals(ejercicio1.getMainMuscle(), "Espalda");
		assertEquals(ejercicio1.getComplexityNumber(), 3);
		assertEquals(ejercicio1.getAllMuscles().size(), 1);
	}
}
