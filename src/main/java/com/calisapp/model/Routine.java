package com.calisapp.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

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
	
	@OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	@JoinColumn(name= "userId", referencedColumnName = "id")
	private User userRoutine;
	
	@JsonBackReference
	@OneToMany(cascade= CascadeType.ALL, orphanRemoval = true)
	private Set<Exercise> exercises = new HashSet<Exercise>();
	
	public Routine() { }
	
	public Routine(String nameRoutine, String generatedBy) {
	    this.nameRoutine = nameRoutine;
	    this.generatedBy = generatedBy;
    	this.exercises = new HashSet<Exercise>();
	}

	/*----------------------------------------------------------------
 		Descripción:	Getter and setter de variables.
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

	public User getUserRoutine() {
		return userRoutine;
	}

	public void setUserRoutine(User userRoutine) {
		this.userRoutine = userRoutine;
	}
}
