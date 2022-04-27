package com.calisapp.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Inheritance
@Table(name = "user")
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique = true, name = "id", updatable = false, nullable = false)
	private Long id;
	
	@NotBlank(message= "Name cannot be blank")
    @Size(min= 4, message= "Name must be longer than 4 characters")
	@Column
	private String name;
	
	@NotBlank(message= "Email cannot be blank")
    @Email(message= "Not a valid email")
	@Column
	private String mail;
	
	@NotBlank(message= "password cannot be blank")
    @Size(min= 4, message= "Password must be longer than 4 characters")
	@Column
	private String password;
	
	@JsonManagedReference
	@ManyToMany(cascade= CascadeType.ALL)
	private List<Routine> routines;
	
	public User() { }

	public User(String aName, String aMail, String aPassword) {
	    this.name = aName;
	    this.mail = aMail;
	    this.password = aPassword;
	    this.routines = new ArrayList<Routine>();
	}
	
	/*----------------------------------------------------------------
		Descripción:	Metodo para generar rutinas a un usuario, con
						el nombre y los ejercicios recibidos por parametro.
						La rutina generada se agrega al usuario.
		Fecha: 			20/04/2022
	----------------------------------------------------------------*/
	public Routine generateRoutine(String nameRoutine, Set<Exercise> ejercicios) {
		Set<Exercise> ejerciciosNuevos =  new HashSet<Exercise>();
		
		for(Exercise exercise:ejercicios) {
			Exercise newExercise = new Exercise(exercise);
			ejerciciosNuevos.add(newExercise);
		}
		
		Routine newRoutine = new RoutineOfUser(nameRoutine, ejerciciosNuevos);
		this.routines.add(newRoutine);
		
		return newRoutine;
	}
	
	/*----------------------------------------------------------------
		Descripción:	Get y Set de variables.
		Fecha: 			20/04/2022
	----------------------------------------------------------------*/
	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
