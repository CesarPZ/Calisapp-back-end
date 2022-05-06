package com.calisapp.services;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.calisapp.model.CalendarUser;
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
	@Autowired
	private CalendarUserService calendarUserService;
	
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
	 	Descripción:	Retorna la rutina con el id recibido
	 					por parametro.
		Fecha: 			20/04/2022
	-------------------------------------------------------*/
	public Routine findByID(Integer id) {
		return this.routineRepository.findById(id).get();
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
	 	 				por parametro y la retorna. Ademas genera un listado de
	 	 				Calendar con dayRoutine y weeksRoutine recibido por parametro.
		Fecha: 			24/04/2022
	-------------------------------------------------------*/
	public Routine createRoutine(Long userId, String nameRoutine, List<Integer> excersicesId, 
									Integer dayRoutine, Integer weeksRoutine) {
		
		User user = this.userService.findByID(userId);
		Set<Exercise> ejercicios = this.exerciseService.findExcersicesByID(excersicesId);
		Routine newRoutine = user.generateRoutine(nameRoutine, ejercicios);
		
		exerciseService.saveAll(newRoutine.getExercises());
		save(newRoutine);
		
		List<CalendarUser> calendarUser = user.addEventTocalendar(dayRoutine, weeksRoutine, newRoutine);
		calendarUserService.saveAll(calendarUser);
		
		return newRoutine;
	}

	/*-------------------------------------------------------
	 	Descripción:	Retorna todas las rutinas del usuario
	 					recibido por parametro
		Fecha: 			24/04/2022
	-------------------------------------------------------*/
	public List<Routine> findWithUserId(Integer idUser) {
		return this.routineRepository.findWithUserId(idUser);
	}

	/*-------------------------------------------------------
	 	Descripción:	Actualiza la rutina, recibe el id de la 
	 					rutina, el nuevo nombre y los nuevos 
	 					nuevos ejercicios.
		Fecha: 			24/04/2022
	-------------------------------------------------------*/
	public Routine editRoutine(Integer idRoutine, String nameRoutine, List<Integer> excersices) {
		Routine routineToUpdate = this.findByID(idRoutine);
		
		Set<Exercise> exercises = this.exerciseService.findExcersicesByID(excersices);
		routineToUpdate.updateRoutine(nameRoutine, exercises);
		
		
		return this.routineRepository.save(routineToUpdate);
	}

	/*-------------------------------------------------------
	 	Descripción:	Quita el ejercicio recibido por parametro
	 					del repositorio de la rotina.
		Fecha: 			24/04/2022
	-------------------------------------------------------*/
	public Routine removeExercise(Integer idRoutine, Integer idExcersice) {
		Routine routineToUpdate = this.findByID(idRoutine);
		Exercise exercise = exerciseService.findByID(idExcersice);
		
		routineToUpdate.removeExercise(exercise);
		
		return this.routineRepository.save(routineToUpdate);
	}
	
	/*-------------------------------------------------------
	 	Descripción:	Agrega el ejercicio recibido por parametro,
	 					a la rutina recibida.
		Fecha: 			24/04/2022
	-------------------------------------------------------*/
	public Routine addExercise(Integer idRoutine, Integer idExcersice) {
		Routine routineToUpdate = this.findByID(idRoutine);
		Exercise exercise = exerciseService.findByID(idExcersice);
		
		routineToUpdate.addExercise(exercise);
		
		return this.routineRepository.save(routineToUpdate);
	}

	/*-------------------------------------------------------
	 	Descripción:	Elimina la rutina recibida por parametro
		Fecha: 			27/04/2022
	-------------------------------------------------------*/
	public void deleteById(Integer idRoutine, Integer idUser) {
		//validar origen de rutina(ADMIN O USER)
		//this.routineRepository.deleteById(id);	
	}

}
