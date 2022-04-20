package com.calisapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.calisapp.model.Routine;
import com.calisapp.repositories.RoutineRepository;

@Service
public class RoutineService {
	@Autowired
	private RoutineRepository  routineRepository;
	
	/*-------------------------------------------------------
 	Descripción:	Retorna todos los ejercicios de la base 
 					de datos.
	Fecha: 			20/04/2022
	-------------------------------------------------------*/
	public List<Routine> findAll() {
		return this.routineRepository.findAll();
	}
}
