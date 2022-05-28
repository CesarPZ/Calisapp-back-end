package com.calisapp.daos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.calisapp.model.Exercise;
import com.calisapp.model.Routine;

public class DayRoutineDAO {

	private Date dayRoutine;
	private String routineName;
	private Integer exerciseNumberDayRoutine;
	private List<Exercise> exercises;
	private Routine routine;
	private Integer idCalendarUser;
	private String estadoDeAnimo;
	
	public DayRoutineDAO() {};
	
	public DayRoutineDAO(Date dayRoutine,Routine routine, Integer exerciseNumberDayRoutine, 
			Integer idCalendarUser, String estadoDeAnimo) {
		this.dayRoutine = dayRoutine;
		this.routineName = routine.getNameRoutine();
		this.exerciseNumberDayRoutine = exerciseNumberDayRoutine;
		this.routine = routine;
		this.idCalendarUser = idCalendarUser;
		this.estadoDeAnimo = estadoDeAnimo;
		List<Exercise> ejercicios = new ArrayList<Exercise>();
		for(Exercise ejercicio: routine.getExercises()) {
			if(ejercicio.getDayExercise() == exerciseNumberDayRoutine){
				ejercicios.add(ejercicio);
			}
		}
		this.exercises = ejercicios;
	}

	public Date getDayRoutine() {
		return dayRoutine;
	}

	public void setDayRoutine(Date dayRoutine) {
		this.dayRoutine = dayRoutine;
	}

	public String getRoutineName() {
		return routineName;
	}

	public void setRoutineName(String routineName) {
		this.routineName = routineName;
	}

	public Integer getExerciseNumberDayRoutine() {
		return exerciseNumberDayRoutine;
	}

	public void setExerciseNumberDayRoutine(Integer exerciseNumberDayRoutine) {
		this.exerciseNumberDayRoutine = exerciseNumberDayRoutine;
	}

	public List<Exercise> getExercises() {
		return exercises;
	}

	public void setExercises (List<Exercise> exercises) {
		this.exercises = exercises;
	}

	public Routine getRoutine() {
		return routine;
	}

	public void setRoutine(Routine routine) {
		this.routine = routine;
	}

	public Integer getIdCalendarUser() {
		return idCalendarUser;
	}

	public void setIdCalendarUser(Integer idCalendarUser) {
		this.idCalendarUser = idCalendarUser;
	}

	public String getEstadoDeAnimo() {
		return estadoDeAnimo;
	}

	public void setEstadoDeAnimo(String estadoDeAnimo) {
		this.estadoDeAnimo = estadoDeAnimo;
	}
}
