package com.calisapp.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
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
@Table(name = "users")
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
	
	@JsonManagedReference
	@ManyToMany(cascade= CascadeType.ALL)
	private List<CalendarUser> calendar;
	
	public User() { }

	public User(String aName, String aMail, String aPassword) {
	    this.name = aName;
	    this.mail = aMail;
	    this.password = aPassword;
	    this.routines = new ArrayList<Routine>();
	    this.calendar =  new ArrayList<CalendarUser>();
	}
	
	public User(UserBuilder userBuilder) {
	    this.name = userBuilder.name;
	    this.mail = userBuilder.mail;
	    this.password = userBuilder.password;
	    this.routines = userBuilder.routines;
	    this.calendar = userBuilder.calendar;
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
	
	public Routine generateRoutineWithExercise(String nameRoutine, Set<Exercise> ejercicios,
			Map<Integer, Integer> daysExercise) {
		
		Set<Exercise> ejerciciosNuevos =  new HashSet<Exercise>();
		
		for(Exercise exercise:ejercicios) {
			Exercise newExercise = new Exercise(exercise);
			if(daysExercise.get(exercise.getId()) != null) {
				newExercise.setDayExercise(daysExercise.get(exercise.getId()));
			}else {
				newExercise.setDayExercise(1);
			}
			ejerciciosNuevos.add(newExercise);
		}
		
		Routine newRoutine = new RoutineOfUser(nameRoutine, ejerciciosNuevos);
		this.routines.add(newRoutine);
		
		return newRoutine;
	}
	
	/*----------------------------------------------------------------
		Descripción:	Metodo para generar CalendarUsers y agregarlos a un usuario, 
						se generan weekRoutine cantidad de CalendarUser 
						y con los dia calculados con dayRoutine; dayRoutine equivale
						a el numero de dia de la semana. 1->Lunes; 2->Martes; 3->Miercoles ...
		Fecha: 			05/05/2022
	----------------------------------------------------------------*/
	public CalendarUser addEventTocalendar(Integer weekRoutine, Routine routine, List<Integer> daysRoutine) {
		Calendar dayFinishRoutine = Calendar.getInstance();
		dayFinishRoutine.add(Calendar.DATE, weekRoutine*7-1);
		
		CalendarUser calendarFinish = new CalendarUser(dayFinishRoutine.getTime(),daysRoutine, weekRoutine ,routine);
		this.calendar.add(calendarFinish);
	
		return calendarFinish;
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
	
	public List<Routine> getRoutines() {
		return routines;
	}

	public void setRoutines(List<Routine> routines) {
		this.routines = routines;
	}
	
	public List<CalendarUser> getCalendar() {
		return calendar;
	}

	public void setCalendar(List<CalendarUser> calendar) {
		this.calendar = calendar;
	}

	/*----------------------------------------------------------------
		Descripción:	Clase builder estatica de User.
		Fecha: 			20/04/2022
	----------------------------------------------------------------*/
	public static class UserBuilder {
		
		private String name;
		private String mail;
		private String password;
		private List<Routine> routines;
		private List<CalendarUser> calendar;
			
		public UserBuilder() {
			this.name = "Julian";
			this.mail = "julian@gmail.com";
			this.password = "miContraseña";
			this.routines = new ArrayList<Routine>();
			this.calendar = new ArrayList<CalendarUser>();
		}
			
	    public UserBuilder withName(String name) {
	        this.name = name;
	        return this;
	    }
	    
	    public UserBuilder withMail(String mail) {
	        this.mail = mail;
	        return this;
	    }
	    
	    public UserBuilder withPassword(String password) {
	        this.password = password;
	        return this;
	    }
	    
	    public UserBuilder withRoutines(List<Routine> routines) {
	        this.routines = routines;
	        return this;
	    }
	    
	    public UserBuilder withCalendar(List<CalendarUser> calendar) {
	        this.calendar = calendar;
	        return this;
	    }
	    
	    public User build() {
	    	User routine =  new User(this);
	        return routine;
	    }
	}
}
