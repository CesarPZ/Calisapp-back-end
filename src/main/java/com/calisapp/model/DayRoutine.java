package com.calisapp.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DayRoutine {

	private Date dayRoutine;
	private String routineName;
	private Integer exerciseNumberDayRoutine;
	private List<Exercise> exercises;
	
	public DayRoutine() {};
	
	public DayRoutine(Date dayRoutine,Routine routine, Integer exerciseNumberDayRoutine) {
		this.dayRoutine = dayRoutine;
		this.routineName = routine.getNameRoutine();
		this.exerciseNumberDayRoutine = exerciseNumberDayRoutine;
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
}
