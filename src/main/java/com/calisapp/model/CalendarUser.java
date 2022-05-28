package com.calisapp.model;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
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
	private Date dayFinishRoutine;
	@Column
	private Date dayInitRoutine;
	@Column
	private Integer weeksRoutine;
	
	@JsonManagedReference
	@ManyToOne(optional = false, cascade = CascadeType.MERGE)
	private Routine routine;
	
	@ElementCollection
	@CollectionTable(
        name="daysRoutine",
        joinColumns=@JoinColumn(name="daysRoutine")
	)
	@Column(name="DAYS")
	private List<Integer> daysRoutine;
	

	@ManyToMany(cascade= CascadeType.ALL)
	private List<DayAndOpinion> dayAndOpinion;

	
	public CalendarUser() {};
	
	public CalendarUser(Date dayFinishRoutine, List<Integer> daysRoutine, Integer weeksRoutine, Routine routine) {
		this.dayFinishRoutine = dayFinishRoutine;
		this.daysRoutine = daysRoutine;
		this.weeksRoutine = weeksRoutine;
		this.routine = routine;
		this.dayInitRoutine = new Date();
		this.dayAndOpinion = new ArrayList<DayAndOpinion>();
	}
	
	public CalendarUser(CalendarUserBuilder builder) {
		this.dayInitRoutine = builder.dayInitRoutine;
		this.weeksRoutine = builder.weeksRoutine;
		this.routine = builder.routine;
		this.daysRoutine = builder.daysRoutinee;
		this.dayAndOpinion = builder.dayAndOpinion;
	}

	public DayAndOpinion addOpinion(String opinion) {
		Date today = new Date();
		LocalDate localDateInitRoutine = today.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date today2 = Date.from(localDateInitRoutine.atStartOfDay(defaultZoneId).toInstant());
		DayAndOpinion dayAndOpinion = new DayAndOpinion(today2,opinion);
		this.dayAndOpinion.add(dayAndOpinion);
		return dayAndOpinion;
	}
	
	/*----------------------------------------------------------------
		Descripción:	Get y Set de variables.
		Fecha: 			24/04/2022
	----------------------------------------------------------------*/
	
	public Integer getId() {
		return id;
	}

	public Date getDayFinishRoutine() {
		return dayFinishRoutine;
	}

	public void setDayFinishRoutine(Date dayFinishRoutine) {
		this.dayFinishRoutine = dayFinishRoutine;
	}

	public Routine getRoutine() {
		return routine;
	}

	public void setRoutine(Routine routine) {
		this.routine = routine;
	}
	
	public Date getDayInitRoutine() {
		return dayInitRoutine;
	}

	public void setDayInitRoutine(Date dayInitRoutine) {
		this.dayInitRoutine = dayInitRoutine;
	}

	public List<Integer> getDaysRoutine() {
		return daysRoutine;
	}

	public void setDaysRoutine(List<Integer> daysRoutine) {
		this.daysRoutine = daysRoutine;
	}

	public Integer getWeeksRoutine() {
		return weeksRoutine;
	}

	public void setWeeksRoutine(Integer weeksRoutine) {
		this.weeksRoutine = weeksRoutine;
	}
	
	public List<DayAndOpinion> getDayAndOpinion() {
		return dayAndOpinion;
	}

	public void setDayAndOpinion(List<DayAndOpinion> dayAndOpinion) {
		this.dayAndOpinion = dayAndOpinion;
	}

	/*----------------------------------------------------------------
		Descripción:	Clase builder estatica de CalendarUser.
		Fecha: 			21/04/2022
	----------------------------------------------------------------*/
	public static class CalendarUserBuilder {
	
		private Date dayInitRoutine;
		private Routine routine;
		private Integer weeksRoutine;
		private List<Integer> daysRoutinee;
		private List<DayAndOpinion> dayAndOpinion;
		
		
		public CalendarUserBuilder() {
			this.dayInitRoutine = new Date();	
			this.routine = new RoutineOfUser();
			this.weeksRoutine = 3;
			this.daysRoutinee = new ArrayList<Integer>();
			this.dayAndOpinion = new ArrayList<DayAndOpinion>();
		}
	    
	    public CalendarUserBuilder withDayInitRoutine (Date dayInitRoutine) {
	        this.dayInitRoutine = dayInitRoutine;
	        return this;
	    }
	    
	    public CalendarUserBuilder withRoutine(Routine routine) {
	        this.routine = routine;
	        return this;
	    }
	    
	    public CalendarUserBuilder withDaysRoutine(List<Integer> daysRoutinee) {
	        this.daysRoutinee = daysRoutinee;
	        return this;
	    }
	    
	    public CalendarUserBuilder withDayAndOpinion(List<DayAndOpinion> dayAndOpinion) {
	        this.dayAndOpinion = dayAndOpinion;
	        return this;
	    }
	    
	    public CalendarUserBuilder withWeeksRoutine(Integer weeksRoutine) {
	        this.weeksRoutine = weeksRoutine;
	        return this;
	    }
	    
	    public CalendarUser build() {
	    	CalendarUser calendarUser =  new CalendarUser(this);
	        return calendarUser;
	    }
	}
}
