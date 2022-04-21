package com.calisapp.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Table(name = "exercise")
/*----------------------------------------------------------------
 	Descripción:	Clase generada para el almacenamiento de ejercicio,
 	  				repeticiones y tiempos.
	Fecha: 			19/04/2022
----------------------------------------------------------------*/
public class Exercise {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	private Integer id;
	
	@Column
	private Integer repetitions;
	@Column
	private String levelExcercise;
	@Column
	private Integer exerciseTime; //Minute
	@Column
	private Integer breakTime; //Minute

	public Exercise() { }
		
	public Exercise(Integer repetitions, String levelExcercise,
					Integer exerciseTime, Integer breakTime) {
		this.repetitions = repetitions;
		this.levelExcercise = levelExcercise;
		this.exerciseTime = exerciseTime;
		this.breakTime = breakTime;
	}
	
	public Exercise(ExerciseBuilder builder) {
		this.repetitions = builder.repetitions;
		this.levelExcercise = builder.levelExcercise;
		this.exerciseTime = builder.exerciseTime;
		this.breakTime = builder.breakTime;
	}
	
	/*----------------------------------------------------------------
		Descripción:	Getter and setter de variables.
		Fecha: 			20/04/2022
	----------------------------------------------------------------*/
	public Integer getRepetitions() {
		return repetitions;
	}

	public void setRepetitions(Integer repetitions) {
		this.repetitions = repetitions;
	}

	public String getLevelExcercise() {
		return levelExcercise;
	}

	public void setLevelExcercise(String levelExcercise) {
		this.levelExcercise = levelExcercise;
	}

	public Integer getExerciseTime() {
		return exerciseTime;
	}

	public void setExerciseTime(Integer exerciseTime) {
		this.exerciseTime = exerciseTime;
	}

	public Integer getBreakTime() {
		return breakTime;
	}

	public void setBreakTime(Integer breakTime) {
		this.breakTime = breakTime;
	}
	
	/*----------------------------------------------------------------
		Descripción:	Clase builder estatica de Exercise.
		Fecha: 			20/04/2022
	----------------------------------------------------------------*/
	public static class ExerciseBuilder {

		private Integer repetitions;
		private String levelExcercise;
		private Integer exerciseTime;
		private Integer breakTime;

			
		public ExerciseBuilder() {
			this.repetitions = 3;
			this.levelExcercise = "principiante";
			this.exerciseTime = 30;
			this.breakTime = 1;
		}
	    
	    public ExerciseBuilder withRepetitions(Integer repetitions) {
	        this.repetitions = repetitions;
	        return this;
	    }
	    
	    public ExerciseBuilder withLevelExcercise (String levelExcercise) {
	        this.levelExcercise = levelExcercise;
	        return this;
	    }
	    
	    public ExerciseBuilder withBreakTime(Integer breakTime) {
	        this.breakTime = breakTime;
	        return this;
	    }
	    
	    public ExerciseBuilder withExerciseTime(Integer exerciseTime) {
	        this.exerciseTime = exerciseTime;
	        return this;
	    }
	    
	    public Exercise build() {
	    	Exercise exercise =  new Exercise(this);
	        return exercise;
	    }
	}
}