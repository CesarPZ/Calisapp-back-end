package com.calisapp.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.ManyToOne;

@Entity
@Inheritance
@Table(name = "exercise")
/*----------------------------------------------------------------
 	Descripción:	Clase generada para el almacenamiento de ejercicio,
 	  				repeticiones y tiempos.
	Fecha: 			19/04/2022
----------------------------------------------------------------*/
public class Exercise {
	
	static final String routineGeneratedByUser = "USER";
	static final String routineGeneratedByApp = "APP";
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	private Integer id;
	
	@Column
	private Integer repetitions;
	@Column
	private Integer series;
	@Column
	private String levelExcercise;
	@Column
	private Integer exerciseTime; //Minute
	@Column
	private Integer breakTime; //Minute
	@Column
	private String generatedBy; //Minute
	
	@JsonManagedReference
	@ManyToOne(optional = false, cascade = CascadeType.MERGE)
	private ProcessExercise processExercise;

	public Exercise() { }
		
	public Exercise(Integer repetitions, Integer series, String levelExcercise,
					Integer exerciseTime, Integer breakTime, String generatedBy) {
		this.repetitions = repetitions;
		this.series = series;
		this.levelExcercise = levelExcercise;
		this.exerciseTime = exerciseTime;
		this.breakTime = breakTime;
		this.generatedBy = generatedBy;
		this.processExercise = new ProcessExercise();
	}
	
	/*----------------------------------------------------------------
		Descripción:	Constructor utilizado para copiar las caracteristicas 
						del exercise recibido, y crear uno nuevo con el campo
						generatedBy = USER.
		Fecha: 			24/04/2022
	----------------------------------------------------------------*/
	public Exercise(Exercise oldExercise) {
		this.repetitions = oldExercise.repetitions;
		this.series = oldExercise.series;
		this.levelExcercise = oldExercise.levelExcercise;
		this.exerciseTime = oldExercise.exerciseTime;
		this.breakTime = oldExercise.breakTime;
		this.generatedBy = routineGeneratedByUser;
		this.processExercise = oldExercise.processExercise;	
	}
	
	public Exercise(ExerciseBuilder builder) {
		this.repetitions = builder.repetitions;
		this.series = builder.series;
		this.levelExcercise = builder.levelExcercise;
		this.exerciseTime = builder.exerciseTime;
		this.breakTime = builder.breakTime;
		this.processExercise = builder.processExercise;
		this.generatedBy = builder.generatedBy;
	}
	
	/*----------------------------------------------------------------
		Descripción:	Get y Set de variables.
		Fecha: 			24/04/2022
	----------------------------------------------------------------*/

	public Integer getId() {
		return id;
	}

	public ProcessExercise getProcessExercise() {
		return processExercise;
	}

	public void setProcessExercise(ProcessExercise processExercise) {
		this.processExercise = processExercise;
	}

	public Integer getRepetitions() {
		return repetitions;
	}
	
	public void setRepetitions(Integer repetitions) {
		this.repetitions = repetitions;
	}
	
	public Integer getSeries() {
		return series;
	}

	public void setSeries(Integer series) {
		this.series = series;
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
	
	public String getGeneratedBy() {
		return generatedBy;
	}

	public void setGeneratedBy(String generatedBy) {
		this.generatedBy = generatedBy;
	}

	/*----------------------------------------------------------------
		Descripción:	Clase builder estatica de Exercise.
		Fecha: 			20/04/2022
	----------------------------------------------------------------*/
	public static class ExerciseBuilder {

		private Integer repetitions;
		private Integer series;
		private String levelExcercise;
		private Integer exerciseTime;
		private Integer breakTime;
		private String generatedBy;
		private ProcessExercise processExercise;

			
		public ExerciseBuilder() {
			this.repetitions = 12;
			this.series = 3;
			this.levelExcercise = "principiante";
			this.exerciseTime = 30;
			this.breakTime = 1;
			this.generatedBy = routineGeneratedByApp;
			this.processExercise = new ProcessExercise();
		}
	    
	    public ExerciseBuilder withRepetitions(Integer repetitions) {
	        this.repetitions = repetitions;
	        return this;
	    }
	    
	    public ExerciseBuilder withSeries(Integer series) {
	        this.series = series;
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
	    
	    public ExerciseBuilder withProcessExercise(ProcessExercise processExercise) {
	        this.processExercise = processExercise;
	        return this;
	    }
	    
	    public ExerciseBuilder withGeneratedBy(String generatedBy) {
	        this.generatedBy = generatedBy;
	        return this;
	    }
	    
	    public Exercise build() {
	    	Exercise exercise =  new Exercise(this);
	        return exercise;
	    }
	}
}