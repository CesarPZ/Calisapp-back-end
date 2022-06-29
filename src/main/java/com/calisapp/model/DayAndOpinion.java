package com.calisapp.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.Table;

@Entity
@Inheritance
@Table(name = "dayAndOpinion")
public class DayAndOpinion {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	private Integer id;
	
	@Column
	private Date dayOpinon;
	@Column
	private String opinion;
	@Column
	private Integer dayNumberRoutine;
	
	public DayAndOpinion() { }
	
	public DayAndOpinion(Date dayOpinon, String opinion, Integer dayNumberRoutine) {
		this.dayOpinon = dayOpinon;
		this.opinion = opinion;
		this.dayNumberRoutine = dayNumberRoutine;
	}
	
	public DayAndOpinion(Date dayOpinon, Integer dayNumberRoutine) {
		this.dayOpinon = dayOpinon;
		this.dayNumberRoutine = dayNumberRoutine;
	}
	
	public DayAndOpinion(DayAndOpinionBuilder builder) {
		this.dayOpinon = builder.dayOpinon;
		this.opinion = builder.opinion;
		this.dayNumberRoutine = builder.dayNumberRoutine;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDayOpinon() {
		return dayOpinon;
	}

	public void setDayOpinon(Date dayOpinon) {
		this.dayOpinon = dayOpinon;
	}

	public String getOpinion() {
		return opinion;
	}

	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}
	
	public Integer getDayNumberRoutine() {
		return dayNumberRoutine;
	}

	public void setDayNumberRoutine(Integer dayNumberRoutine) {
		this.dayNumberRoutine = dayNumberRoutine;
	}

	/*----------------------------------------------------------------
		Descripción:	Clase builder estatica de CalendarUser.
		Fecha: 			21/04/2022
	----------------------------------------------------------------*/
	public static class DayAndOpinionBuilder {
	
		private Date dayOpinon;
		private String opinion;
		private Integer dayNumberRoutine;
		
		public DayAndOpinionBuilder() {
			this.dayOpinon = new Date();	
			this.opinion = "Buena";
		}
	    
	    public DayAndOpinionBuilder withDayOpinon (Date dayOpinon) {
	        this.dayOpinon = dayOpinon;
	        return this;
	    }
	    
	    public DayAndOpinionBuilder withOpinion(String opinion) {
	        this.opinion = opinion;
	        return this;
	    }
	    
	    public DayAndOpinionBuilder withDayNumberRoutine(Integer dayNumberRoutine) {
	        this.dayNumberRoutine = dayNumberRoutine;
	        return this;
	    }
	    
	    public DayAndOpinion build() {
	    	DayAndOpinion dayAndOpinion =  new DayAndOpinion(this);
	        return dayAndOpinion;
	    }
	}
}
