package com.calisapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.calisapp.model.ProcessExercise;
import com.calisapp.repositories.ProcessExerciseRepository;

@Service
public class ProcessExerciseService {
	@Autowired
	private ProcessExerciseRepository processExerciseRepository;
	
	/*-------------------------------------------------------
 	Descripción:	Retorna todos los ejercicios y caracteristicas 
 					de la base de datos.
	Fecha: 			20/04/2022
	-------------------------------------------------------*/
	public List<ProcessExercise> findAll() {
		return this.processExerciseRepository.findAll();
	}
	
	@Transactional
	public ProcessExercise save(ProcessExercise model) {
		return processExerciseRepository.save(model);
	}
}