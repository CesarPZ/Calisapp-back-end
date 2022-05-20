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
	
	public RoutineByLevel (String nameRoutine, String level) {
		 super(nameRoutine, routineGeneratedBy);
	}
	
	public RoutineByLevel (RoutineByLevelBuilder builder) {
		super.setNameRoutine(builder.nameRoutine);
		super.setExercises(builder.exercises);
		super.setLevel(builder.level);
		super.setGeneratedBy(routineGeneratedBy);
		super.setOpinion(builder.opinion);
	}
	
	/*----------------------------------------------------------------
		Descripción:	Clase builder estatica de RoutineByLevel.
		Fecha: 			20/04/2022
	----------------------------------------------------------------*/
	public static class RoutineByLevelBuilder {
		private String nameRoutine;
		private Set<Exercise> exercises;
		private String level;
		private Integer opinion;
			
		public RoutineByLevelBuilder() {
			this.nameRoutine = "Rutina Avanzada";
			this.exercises = new HashSet<Exercise>();
			this.level= "Avanzado";
			this.opinion=0;
		}
			
	    public RoutineByLevelBuilder withNameRoutine(String nameRoutinee) {
	        this.nameRoutine = nameRoutinee;
	        return this;
	    }
	    
	    public RoutineByLevelBuilder withExercises(Set<Exercise> exercises) {
	        this.exercises = exercises;
	        return this;
	    }
	    
	    public RoutineByLevelBuilder withLevel(String level) {
	        this.level = level;
	        return this;
	    }
	    
	    public RoutineByLevelBuilder withOpinion(Integer opinion) {
	        this.opinion = opinion;
	        return this;
	    }
	    
	    public RoutineByLevel build() {
	    	RoutineByLevel routine =  new RoutineByLevel(this);
	        return routine;
	    }
	}
}
