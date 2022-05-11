package com.calisapp.services;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.calisapp.model.CalendarUser;
import com.calisapp.model.DayRoutine;
import com.calisapp.model.Exercise;
import com.calisapp.model.Routine;
import com.calisapp.repositories.CalendarUserRepository;

@Service
public class CalendarUserService {
	@Autowired
	private CalendarUserRepository  calendarUserRepository;
	
	public List<CalendarUser> findAll() {
		return (List<CalendarUser>) this.calendarUserRepository.findAll();
	}

	public Iterable<CalendarUser> saveAll(List<CalendarUser> calendarUser) {
		return calendarUserRepository.saveAll(calendarUser);
	}

	public List<DayRoutine> findWithUserId(Integer idUser) {
		List<DayRoutine> daysRoutine = new  ArrayList<DayRoutine>();
		List<CalendarUser> calendars = this.calendarUserRepository.findWithUserId(idUser);
		List<Date> scheduledDays = new ArrayList<Date>();
		
		for(CalendarUser calendar : calendars) {
			List<Integer> numberDaysRotine = this.calculateDaysExercise(calendar.getRoutine());
			for(Integer dayNumber: calendar.getDaysRoutine()) {
				scheduledDays.addAll(calculateScheduledDays(dayNumber,calendar.getWeeksRoutine(), calendar.getDayInitRoutine()));
			}
			daysRoutine.addAll(this.createDaysRoutine(scheduledDays, numberDaysRotine, calendar.getRoutine()));
		}
		return daysRoutine;
	}

	private List<Integer> calculateDaysExercise(Routine routine) {
		Set<Integer> diasDeRoutine = new HashSet<Integer> ();
		
		for(Exercise ejercicio:routine.getExercises()) {
			diasDeRoutine.add(ejercicio.getDayExercise());
		}
		
		List<Integer> listDays = new ArrayList<Integer>();
		listDays.addAll(diasDeRoutine);
		
		return listDays;
	}

	private List<Date> calculateScheduledDays(Integer dayNumber, Integer weeksRoutine, Date dateInitRoutine) {
		List<Date> scheduledDays = new ArrayList<Date>();
		LocalDate localDateInitRoutine = dateInitRoutine.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate firstDayOfExercise = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();

		for(int i=0; i<6; i++) {
			localDateInitRoutine = localDateInitRoutine.plusDays(1);
			if(localDateInitRoutine.getDayOfWeek().getValue() == dayNumber) {
				firstDayOfExercise = localDateInitRoutine;
			}
		}
		for(int i = 0; i<weeksRoutine; i++) {
			LocalDate dayRoutine = firstDayOfExercise.plusDays(i*7);
			scheduledDays.add(Date.from(dayRoutine.atStartOfDay(defaultZoneId).toInstant()));
		}
		return scheduledDays;
	}
	
	private List<DayRoutine> createDaysRoutine(List<Date> scheduledDays, 
			 								List<Integer> numberDaysRotine, Routine routine){
		
		List<DayRoutine> dayRoutines = new ArrayList<DayRoutine>();
		List<Date> scheduledDaysSorted = scheduledDays.stream()
					  .sorted(Comparator.comparingLong(Date::getTime))
					  .collect(Collectors.toList());
		Integer dayPos = 1;
		for(Date day: scheduledDaysSorted) {
			Integer pos = this.numberRoutineCorresponding(dayPos,numberDaysRotine);
			DayRoutine diaDeRutina = new DayRoutine(day,routine, pos);
			dayRoutines.add(diaDeRutina);
			dayPos = dayPos + 1;
		}
		
		return dayRoutines;
	}

	private Integer numberRoutineCorresponding(Integer dayPos, List<Integer> numberDaysRotine) {
		
		Integer dayPosition = dayPos;
		Integer valueFinish = 0;
		while(dayPosition > 0) {
			if(dayPosition <= numberDaysRotine.size()) {
				valueFinish = numberDaysRotine.get(dayPosition-1);
				break;
			}else {
				dayPosition = dayPosition - numberDaysRotine.size();
			}
		}
		
		return valueFinish;
		
	}

	@Transactional
	public CalendarUser save(CalendarUser model) {
		return calendarUserRepository.save(model);
	}
}
