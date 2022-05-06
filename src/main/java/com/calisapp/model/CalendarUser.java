package com.calisapp.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Inheritance
@Table(name = "calendarUser")
/*----------------------------------------------------------------
 	Descripción:	Clase generada para el almacenamiento 
 					de fechas con rutinas del user.
	Fecha: 			20/04/2022
----------------------------------------------------------------*/
public class CalendarUser {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	private Integer id;
	
	@Column
	private Date dateRoutine;
	
	@JsonManagedReference
	@ManyToOne(optional = false, cascade = CascadeType.MERGE)
	private Routine routine;
	
	public CalendarUser() { }
		
	public CalendarUser(Date dateRoutine, Routine routine) {
		this.dateRoutine = dateRoutine;
		this.routine = routine;
	}
	
	public CalendarUser(CalendarUserBuilder builder) {
		this.dateRoutine = builder.dateRoutine;
		this.routine = builder.routine;
	}

	/*----------------------------------------------------------------
		Descripción:	Get y Set de variables.
		Fecha: 			24/04/2022
	----------------------------------------------------------------*/
	
	public Integer getId() {
		return id;
	}

	public Date getDateRoutine() {
		return dateRoutine;
	}

	public void setDateRoutine(Date dateRoutine) {
		this.dateRoutine = dateRoutine;
	}

	public Routine getRoutine() {
		return routine;
	}

	public void setRoutine(Routine routine) {
		this.routine = routine;
	}

	/*----------------------------------------------------------------
		Descripción:	Clase builder estatica de CalendarUser.
		Fecha: 			21/04/2022
	----------------------------------------------------------------*/
	public static class CalendarUserBuilder {
	
		private Date dateRoutine;
		private Routine routine;
		
		public CalendarUserBuilder() {
			this.dateRoutine = new Date();	
			this.routine = new RoutineOfUser();
		}
			
	    public CalendarUserBuilder withDateRoutine(Date dateRoutine) {
	        this.dateRoutine = dateRoutine;
	        return this;
	    }
	    
	    public CalendarUserBuilder withRoutine(Routine routine) {
	        this.routine = routine;
	        return this;
	    }
	    
	    public CalendarUser build() {
	    	CalendarUser calendarUser =  new CalendarUser(this);
	        return calendarUser;
	    }
	}
}
