package com.calisapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.calisapp.model.Exercise;
import com.calisapp.repositories.ExerciseRepository;

@Service
public class ExerciseService {
	@Autowired
	private ExerciseRepository  exerciseRepository;
	
	/*-------------------------------------------------------
 	Descripción:	Retorna todos los ejercicios de la base 
 					de datos.
	Fecha: 			20/04/2022
	-------------------------------------------------------*/
	public List<Exercise> findAll() {
		return this.exerciseRepository.findAll();
	}
	
	@Transactional
	public Exercise save(Exercise model) {
		return exerciseRepository.save(model);
	}

	public List<Exercise> findExerciseByRoutine(String idRoutine) {
		return this.exerciseRepository.findExcersiteToRoutine(idRoutine);
	}

}
