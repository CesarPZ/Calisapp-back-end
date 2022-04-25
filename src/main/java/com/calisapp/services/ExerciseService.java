package com.calisapp.services;

import java.util.List;
import java.util.Set;

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
		return (List<Exercise>) this.exerciseRepository.findAll();
	}
	
	@Transactional
	public Exercise save(Exercise model) {
		return exerciseRepository.save(model);
	}

	public List<Exercise> findExerciseByRoutine(String idRoutine) {
		return this.exerciseRepository.findExerciseToRoutine(idRoutine);
	}

	/*-------------------------------------------------------
 	Descripción:	Retorna los exercise con el id recibido por 
 					parametro.
	Fecha: 			24/04/2022
	-------------------------------------------------------*/
	public Set<Exercise> findExcersicesByID(List<Integer> excersicesId) {
		return this.exerciseRepository.findExercisesToId(excersicesId);
	}

	public Exercise findByID(Integer idExcersice) {
		return this.exerciseRepository.findById(idExcersice).get();
	}
	
}
