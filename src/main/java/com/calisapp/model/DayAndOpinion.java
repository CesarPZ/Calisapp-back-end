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
	
	public DayAndOpinion() { }
	
	public DayAndOpinion(Date dayOpinon, String opinion) {
		this.dayOpinon = dayOpinon;
		this.opinion = opinion;
	}

	public DayAndOpinion(DayAndOpinionBuilder builder) {
		this.dayOpinon = builder.dayOpinon;
		this.opinion = builder.opinion;
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
	/*----------------------------------------------------------------
		Descripción:	Clase builder estatica de CalendarUser.
		Fecha: 			21/04/2022
	----------------------------------------------------------------*/
	public static class DayAndOpinionBuilder {
	
		private Date dayOpinon;
		private String opinion;
		
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
	    
	    public DayAndOpinion build() {
	    	DayAndOpinion dayAndOpinion =  new DayAndOpinion(this);
	        return dayAndOpinion;
	    }
	}
}
