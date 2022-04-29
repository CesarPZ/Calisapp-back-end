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
	static final String routineGeneratedByUser = "USER";
	static final String routineGeneratedByApp = "APP";
	
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
	
	public Iterable<Exercise> saveAll(Set<Exercise> exercises) {
		return exerciseRepository.saveAll(exercises);
	}

	public List<Exercise> findExerciseByRoutine(String idRoutine) {
		return this.exerciseRepository.findExerciseToRoutine(idRoutine);
	}

	public Exercise findByID(Integer idExcersice) {
		return this.exerciseRepository.findById(idExcersice).get();
	}
	
	/*-------------------------------------------------------
	 	Descripción:	Retorna los exercise con el id recibido por 
	 					parametro.
		Fecha: 			24/04/2022
	-------------------------------------------------------*/
	public Set<Exercise> findExcersicesByID(List<Integer> excersicesId) {
		return this.exerciseRepository.findExercisesToId(excersicesId);
	}
	
	/*-------------------------------------------------------
	 	Descripción:	Retorna todos los ejercicios de la base 
	 					de datos que fueron creados por la aplicacion.
		Fecha: 			27/04/2022
	-------------------------------------------------------*/
	public List<Exercise> findAllToGeneratedByApp() {
		return (List<Exercise>) this.exerciseRepository.findAllToGeneratedByApp(routineGeneratedByApp);
	}

	/*-------------------------------------------------------
	 	Descripción:	Actualiza el ejercicio recibido por parametro;
	 					actualiza los campos series y repeticiones.
		Fecha: 			28/04/2022
	-------------------------------------------------------*/
	public Exercise updateExercise(Integer exerciseId, Integer series, Integer repetitions) {
		Exercise exerciseToUpdate = this.findByID(exerciseId);
		exerciseToUpdate.setSeries(series);
		exerciseToUpdate.setRepetitions(repetitions);
		
		return this.exerciseRepository.save(exerciseToUpdate);
	}
}
