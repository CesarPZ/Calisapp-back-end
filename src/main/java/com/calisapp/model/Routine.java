package com.calisapp.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Inheritance
@Table(name = "routines")
/*----------------------------------------------------------------
 	Descripción:	Clase abstracta generada para el almacenamiento 
 					de rutinas.
	Fecha: 			20/04/2022
----------------------------------------------------------------*/
public abstract class Routine {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	private Integer id;
	@Column
	private String nameRoutine;
	@Column
	private String generatedBy;
	@Column
	private String level;
	@JsonManagedReference
	@ManyToMany(cascade= CascadeType.ALL)
	private Set<Exercise> exercises;
	
	public Routine() { }
	
	public Routine(String nameRoutine, String generatedBy) {
	    this.nameRoutine = nameRoutine;
	    this.generatedBy = generatedBy;
		this.exercises = new HashSet<Exercise>();
	}
	
	public Routine(String nameRoutine, Set<Exercise> ejercicios, String generatedBy) {
	    this.nameRoutine = nameRoutine;
	    this.generatedBy = generatedBy;
		this.exercises = ejercicios;
	}
	
	/*----------------------------------------------------------------
		Descripción:	Actualizar la rutina con el nameRoutine y los 
						ejercicios recibidos por paramtro.
		Fecha: 			24/04/2022
	----------------------------------------------------------------*/
	public void updateRoutine(String nameRoutine, Set<Exercise> exercises) {
		if(nameRoutine !=null) {
			this.setNameRoutine(nameRoutine);
		}
		
		if(exercises !=null && !exercises.isEmpty()) {
			this.setExercises(exercises);
		}
	}
	
	/*----------------------------------------------------------------
		Descripción:	Eliminar exercise recibido por parametro a la  
						lista de excersice de la rutina.
		Fecha: 			24/04/2022
	----------------------------------------------------------------*/
	public void removeExercise(Exercise excersiceDeleted) {
		
		exercises.remove(excersiceDeleted);
		
	}
	
	/*----------------------------------------------------------------
		Descripción:	Agregar exercise recibido por parametro a la  
						lista de excersice de la rutina.
		Fecha: 			24/04/2022
	----------------------------------------------------------------*/
	public void addExercise(Exercise newExcersice) {
		
		exercises.add(newExcersice);
		
	}
	/*----------------------------------------------------------------
 		Descripción:	Get y Set de variables.
		Fecha: 			20/04/2022
	----------------------------------------------------------------*/
	public Integer getId() {
		return id;
	}
	
	public String getNameRoutine() {
		return nameRoutine;
	}

	public void setNameRoutine(String nameRoutine) {
		this.nameRoutine = nameRoutine;
	}

	public String getGeneratedBy() {
		return generatedBy;
	}

	public void setGeneratedBy(String generatedBy) {
		this.generatedBy = generatedBy;
	}

	public Set<Exercise> getExercises() {
		return exercises;
	}

	public void setExercises(Set<Exercise> exercises) {
		this.exercises = exercises;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}
}
