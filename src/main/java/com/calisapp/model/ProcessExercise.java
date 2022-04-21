package com.calisapp.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
@Entity
@Table(name = "processexercise")
/*----------------------------------------------------------------
 	Descripción:	Clase generada para el almacenamiento de ejercicio,
 	  				con sus caracteristicas.
	Fecha: 			21/04/2022
----------------------------------------------------------------*/
public class ProcessExercise {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	private Integer id;
	@Column
	private String nameExercise;
	@Column
	private String description;
	@Column
	private String urlVideo;
	@Column
	private String mainMuscle;
	
	@ElementCollection
	@CollectionTable(
	        name="PHONE",
	        joinColumns=@JoinColumn(name="OWNER_ID")
	  )
	@Column(name="PHONE_NUMBER")
	private Set<String> allMuscles;
	@Column
	private Integer complexityNumber; //between 1 and 5
	
	public ProcessExercise() { }
		
	public ProcessExercise(String nameExercise, String description,
							String urlVideo, String mainMuscle, 
							Integer complexityNumber) {
		this.nameExercise = nameExercise;
		this.description = description;
		this.urlVideo = urlVideo;
		this.mainMuscle = mainMuscle;
		this.allMuscles = new HashSet<String>();
		this.complexityNumber = complexityNumber;
	}
	
	public ProcessExercise(ProcessExerciseBuilder builder) {
		this.nameExercise = builder.nameExercise;
		this.description = builder.description;
		this.urlVideo = builder.urlVideo;
		this.mainMuscle = builder.mainMuscle;
		this.allMuscles = builder.allMuscles;
		this.complexityNumber = builder.complexityNumber;
	}
	
	/*----------------------------------------------------------------
		Descripción:	Getter and setter de variables.
		Fecha: 			21/04/2022
	----------------------------------------------------------------*/
	public String getNameExercise() {
		return nameExercise;
	}

	public void setNameExercise(String nameExercise) {
		this.nameExercise = nameExercise;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrlVideo() {
		return urlVideo;
	}

	public void setUrlVideo(String urlVideo) {
		this.urlVideo = urlVideo;
	}

	public String getMainMuscle() {
		return mainMuscle;
	}

	public void setMainMuscle(String mainMuscle) {
		this.mainMuscle = mainMuscle;
	}

	public Set<String> getAllMuscles() {
		return allMuscles;
	}

	public void setAllMuscles(Set<String> allMuscles) {
		this.allMuscles = allMuscles;
	}

	public Integer getComplexityNumber() {
		return complexityNumber;
	}

	public void setComplexityNumber(Integer complexityNumber) {
		this.complexityNumber = complexityNumber;
	}

	/*----------------------------------------------------------------
		Descripción:	Clase builder estatica de ProcessExercise.
		Fecha: 			21/04/2022
	----------------------------------------------------------------*/
	public static class ProcessExerciseBuilder {

		private String nameExercise;
		private String description;
		private String urlVideo;
		private String mainMuscle;
		private Set<String> allMuscles;
		private Integer complexityNumber;
			
		public ProcessExerciseBuilder() {
			this.nameExercise = "Flexiones diamante";	
			this.description = "Flexiones pero con las manos unidas formando la figura de un diamante";
			this.urlVideo = "https://github.com/CesarPZ/Calisapp-back-end";
			this.mainMuscle = "Espalda";
			this.allMuscles = new HashSet<String>();
			this.complexityNumber = 3;
		}
			
	    public ProcessExerciseBuilder withNameExercise(String nameExercise) {
	        this.nameExercise = nameExercise;
	        return this;
	    }
	    
	    public ProcessExerciseBuilder withDescription(String description) {
	        this.description = description;
	        return this;
	    }
	    
	    public ProcessExerciseBuilder withUrlVideo(String urlVideo) {
	        this.urlVideo = urlVideo;
	        return this;
	    }
	    
	    public ProcessExerciseBuilder withMainMuscle(String mainMuscle) {
	        this.mainMuscle = mainMuscle;
	        return this;
	    }
	    
	    public ProcessExerciseBuilder withComplexityNumber(Integer complexityNumber) {
	        this.complexityNumber = complexityNumber;
	        return this;
	    }
	    
	    public ProcessExerciseBuilder withAllMuscles(Set<String> allMuscles) {
	        this.allMuscles = allMuscles;
	        return this;
	    }
	    
	    public ProcessExercise build() {
	    	ProcessExercise processExercise =  new ProcessExercise(this);
	        return processExercise;
	    }
	}
}