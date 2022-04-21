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
public class RoutineOfUser extends Routine{

	static final String routineGeneratedBy = "USER";
	
	public RoutineOfUser () {}
	
	public RoutineOfUser (String nameRoutine) {
		 super(nameRoutine, routineGeneratedBy);
	}
	
	public RoutineOfUser (RoutineOfUserBuilder builder) {		
		super.setNameRoutine(builder.nameRoutine);
		super.setExercises(builder.exercises);
		super.setGeneratedBy(routineGeneratedBy);
		super.setUserRoutine(builder.userRoutine);
	}
	
	/*----------------------------------------------------------------
		Descripción:	Clase builder estatica de RoutineOfUser.
		Fecha: 			20/04/2022
	----------------------------------------------------------------*/
	public static class RoutineOfUserBuilder {
		
		private String nameRoutine;
		private Set<Exercise> exercises;
		private User userRoutine;
			
		public RoutineOfUserBuilder() {
			this.nameRoutine = "Rutina Avanzada";
			this.exercises = new HashSet<Exercise>();
			this.userRoutine = null;
		}
			
	    public RoutineOfUserBuilder withNameRoutine(String nameRoutinee) {
	        this.nameRoutine = nameRoutinee;
	        return this;
	    }
	    
	    public RoutineOfUserBuilder withExercises(Set<Exercise> exercises) {
	        this.exercises = exercises;
	        return this;
	    }
	    
	    public RoutineOfUserBuilder withUserRoutine(User userRoutine) {
	        this.userRoutine = userRoutine;
	        return this;
	    }
	    
	    public RoutineOfUser build() {
	    	RoutineOfUser routine =  new RoutineOfUser(this);
	        return routine;
	    }
	}
	
}