package com.calisapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.calisapp.model.Routine;
import com.calisapp.repositories.RoutineRepository;

@Service
public class RoutineService {
	@Autowired
	private RoutineRepository routineRepository;
	
	static final String routineGeneratedByApp = "APP";
	static final String routineGeneratedByUser= "USER";
	
	/*-------------------------------------------------------
 	Descripción:	Retorna todas las rutinas de la base 
 					de datos.
	Fecha: 			20/04/2022
	-------------------------------------------------------*/
	public List<Routine> findAll() {
		return this.routineRepository.findAll();
	}
	
	/*-------------------------------------------------------
 	Descripción:	Retorna todas las rutinas con el "level" recibido 
 					por parametro de la base de datos.
	Fecha: 			20/04/2022
	-------------------------------------------------------*/
	public List<Routine> findWithLevel(String level) {
		return this.routineRepository.findWithLevel(level, routineGeneratedByApp);
	}
}
