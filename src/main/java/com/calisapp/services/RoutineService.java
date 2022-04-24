package com.calisapp.services;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.calisapp.model.Exercise;
import com.calisapp.model.Routine;
import com.calisapp.model.User;
import com.calisapp.repositories.RoutineRepository;

@Service
public class RoutineService {
	@Autowired
	private RoutineRepository routineRepository;
	@Autowired
	private UserService userService;
	@Autowired
	private ExerciseService exerciseService;
	
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

	/*-------------------------------------------------------
 	Descripción:	Guarda una routine.
	Fecha: 			24/04/2022
	-------------------------------------------------------*/
	@Transactional
	public Routine save(Routine model) {
		return routineRepository.save(model);
	}

	/*-------------------------------------------------------
 	Descripción:	Genera una rutina con los ejercicios recibidos
 	 				por parametro y la retorna.
	Fecha: 			24/04/2022
	-------------------------------------------------------*/
	public Routine createRoutine(Long userId, String nameRoutine, List<Integer> excersicesId) {
		
		User user = this.userService.findByID(userId);
		Set<Exercise> ejercicios = this.exerciseService.findExcersicesByID(excersicesId);
		
		Routine newRoutine = user.generateRoutine(nameRoutine, ejercicios);
		
		return save(newRoutine);
	}

	/*-------------------------------------------------------
 	Descripción:	Retorna todas las rutinas del usuario
 					recibido por parametro
	Fecha: 			24/04/2022
	-------------------------------------------------------*/
	public List<Routine> findWithUserId(Integer idUser) {
		return this.routineRepository.findWithUserId(idUser);
	}

}
