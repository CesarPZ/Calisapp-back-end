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
 	  				con sus caracteristicas.
	Fecha: 			19/04/2022
----------------------------------------------------------------*/
public class Exercise {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	private Integer id;
	
	@Column
	private String nameExercise;
	@Column
	private Integer complexityNumber; //between 1 and 5
	@Column
	private String description;
	@Column
	private String urlVideo;
	@Column
	private String muscle;

	public Exercise() { }
		
	public Exercise(String nameExercise, Integer complexityNumber, 
			String description, String urlVideo, String muscle) {
		this.nameExercise = nameExercise;
		this.complexityNumber = complexityNumber;
		this.description = description;
		this.urlVideo = urlVideo;
		this.muscle = muscle;
	}
	
	public Exercise(ExerciseBuilder builder) {
		this.nameExercise = builder.nameExercise;
		this.complexityNumber = builder.complexityNumber;
		this.description = builder.description;
		this.urlVideo = builder.urlVideo;
		this.muscle = builder.muscle;
	}
	
	/*----------------------------------------------------------------
		Descripción:	Getter and setter de variables.
		Fecha: 			20/04/2022
	----------------------------------------------------------------*/
	public Integer getId() {
		return id;
	}

	public String getnameExercise() {
		return nameExercise;
	}

	public void setnameExercise(String nameExercise) {
		this.nameExercise = nameExercise;
	}

	public Integer getComplexityNumber() {
		return complexityNumber;
	}

	public void setComplexityNumber(Integer complexityNumber) {
		this.complexityNumber = complexityNumber;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getMuscle() {
		return muscle;
	}

	public void setMuscle(String muscle) {
		this.muscle = muscle;
	}
	public String getUrlVideo() {
		return urlVideo;
	}

	public void setUrlVideo(String urlVideo) {
		this.urlVideo = urlVideo;
	}

	/*----------------------------------------------------------------
		Descripción:	Clase builder estatica de Exercise.
		Fecha: 			20/04/2022
	----------------------------------------------------------------*/
	public static class ExerciseBuilder {
		
		private String nameExercise;
		private Integer complexityNumber;
		private String description;
		private String urlVideo;
		private String muscle;
			
		public ExerciseBuilder() {
			this.nameExercise = "Flexiones diamante";	
			this.complexityNumber = 3;
			this.description = "Flexiones pero con las manos unidas formando la figura de un diamante";
			this.urlVideo = "https://github.com/CesarPZ/Calisapp-back-end";
			this.muscle = "Espalda";
		}
			
	    public ExerciseBuilder withNameExercise(String nameExercise) {
	        this.nameExercise = nameExercise;
	        return this;
	    }
	    
	    public ExerciseBuilder withComplexityNumber(Integer complexityNumber) {
	        this.complexityNumber = complexityNumber;
	        return this;
	    }
	    
	    public ExerciseBuilder withDescription(String description) {
	        this.description = description;
	        return this;
	    }
	    
	    public ExerciseBuilder withUrlVideo(String urlVideo) {
	        this.urlVideo = urlVideo;
	        return this;
	    }
	    
	    public ExerciseBuilder withMuscle(String muscle) {
	        this.muscle = muscle;
	        return this;
	    }
	    
	    public Exercise build() {
	    	Exercise exercise =  new Exercise(this);
	        return exercise;
	    }
	}
}