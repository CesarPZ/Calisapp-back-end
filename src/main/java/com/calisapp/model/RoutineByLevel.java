package com.calisapp.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;


@Entity
/*----------------------------------------------------------------
	Descripción:	Clase generada para el almacenamiento de rutinas
					ya precargadas en la aplicacion, y con un nivel de
					avance del usuario.
	Fecha: 			20/04/2022
----------------------------------------------------------------*/
public class RoutineByLevel extends Routine{

	static final String routineGeneratedBy = "APP";
	
	public RoutineByLevel () {}
	
	public RoutineByLevel (String nameRoutine) {
		 super(nameRoutine, routineGeneratedBy);
	}
	
	public RoutineByLevel (RoutineByLevelBuilder builder) {
		super.nameRoutine = builder.nameRoutine;
		super.exercises = builder.exercises;
		super.generatedBy = routineGeneratedBy;	
	}
	
	/*----------------------------------------------------------------
		Descripción:	Clase builder estatica de RoutineByLevel.
		Fecha: 			20/04/2022
	----------------------------------------------------------------*/
	public static class RoutineByLevelBuilder {
		
		private String nameRoutine;
		private Set<Exercise> exercises;
			
		public RoutineByLevelBuilder() {
			this.nameRoutine = "Rutina Avanzada";
			this.exercises = new HashSet<Exercise>();
		}
			
	    public RoutineByLevelBuilder withNameRoutine(String nameRoutinee) {
	        this.nameRoutine = nameRoutinee;
	        return this;
	    }
	    
	    public RoutineByLevelBuilder withExercises(Set<Exercise> exercises) {
	        this.exercises = exercises;
	        return this;
	    }
	    
	    public RoutineByLevel build() {
	    	RoutineByLevel routine =  new RoutineByLevel(this);
	        return routine;
	    }
	}
}
