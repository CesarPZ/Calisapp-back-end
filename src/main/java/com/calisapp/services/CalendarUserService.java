package com.calisapp.services;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.calisapp.daos.DayRoutineDAO;
import com.calisapp.exceptions.ResourceNotFoundException;
import com.calisapp.model.CalendarUser;
import com.calisapp.model.DayAndOpinion;
import com.calisapp.model.Exercise;
import com.calisapp.model.Routine;
import com.calisapp.model.User;
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

	/*-------------------------------------------------------
	 	Descripción: Genera una lista de DayRoutina, calculando
	 				la fecha y los ejercicios correspondientes a ese 
	 				dia. 
		Fecha: 		11/05/2022
	-------------------------------------------------------*/
	public List<DayRoutineDAO> findWithUserId(Long idUser) {
		List<DayRoutineDAO> daysRoutine = new  ArrayList<DayRoutineDAO>();
		List<CalendarUser> calendars = this.calendarUserRepository.findWithUserId(idUser);
		
		for(CalendarUser calendar : calendars) {
			Set<Date> scheduledDays = this.calculateCalendarDays(calendar);
			List<Integer> numberDaysRotine = this.calculateDaysExercise(calendar.getRoutine());
			
			daysRoutine.addAll(this.createDaysRoutine(scheduledDays, numberDaysRotine, calendar));
		}
		return daysRoutine;
	}
	
	/*--------------------------------------------------------------------
	 	Descripción: Calcula todos los dias que se deben realizar ejecicios,
	 	 			y los retorna en una lista.
		Fecha: 		11/05/2022
	--------------------------------------------------------------------*/
	private Set<Date> calculateCalendarDays(CalendarUser calendar) {
		Set<Date> scheduledDays = new HashSet<Date>();
		for(Integer dayNumber: calendar.getDaysRoutine()) {
			scheduledDays.addAll(this.calculateScheduledDays(dayNumber,calendar.getWeeksRoutine(), calendar.getDayInitRoutine()));
		}
		return scheduledDays;
	}
	
	/*--------------------------------------------------------------------
	 	Descripción: Calcula las fechas intermedias entre el dateInitRoutine
	 				hasta la cantidad de semanas recibidas por paramtro, que 
	 				sean del dia dayNumber (1-Lunes; 2-Martes; 3-Miercoles ...)
		Fecha: 		11/05/2022
	--------------------------------------------------------------------*/
	private Set<Date> calculateScheduledDays(Integer dayNumber, Integer weeksRoutine, Date dateInitRoutine) {
		Set<Date> scheduledDays = new HashSet<Date>();
		LocalDate localDateInitRoutine = dateInitRoutine.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate firstDayOfExercise = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();

		for(int i=0; i<6; i++) {
			if(localDateInitRoutine.getDayOfWeek().getValue() == dayNumber) {
				firstDayOfExercise = localDateInitRoutine;
			}
			localDateInitRoutine = localDateInitRoutine.plusDays(1);
		}
		for(int i = 0; i<weeksRoutine; i++) {
			LocalDate dayRoutine = firstDayOfExercise.plusDays(i*7);
			scheduledDays.add(Date.from(dayRoutine.atStartOfDay(defaultZoneId).toInstant()));
		}
		return scheduledDays;
	}
	
	/*--------------------------------------------------------------------
	 	Descripción: Retorna un listado con los todos los dias que tiene la rutina
	 				sin repetir, en una lista.
		Fecha: 		11/05/2022
	--------------------------------------------------------------------*/
	private List<Integer> calculateDaysExercise(Routine routine) {
		Set<Integer> diasDeRoutine = new HashSet<Integer> ();
		
		for(Exercise ejercicio:routine.getExercises()) {
			if(ejercicio.getDayExercise() != null) {
				diasDeRoutine.add(ejercicio.getDayExercise());
			}
		}
		
		List<Integer> listDays = new ArrayList<Integer>();
		listDays.addAll(diasDeRoutine);
		
		return listDays;
	}
	
	/*--------------------------------------------------------------------
	 	Descripción: Genera listado de DayRoutineDAO con las fechas recibidas por
	 				parametro y los ejercicios correspondientes a la rutina el 
	 				dia numberDaysRotine, segun el orden de fechas de scheduledDays.
		Fecha: 		11/05/2022
	--------------------------------------------------------------------*/
	private List<DayRoutineDAO> createDaysRoutine(Set<Date> scheduledDays, List<Integer> numberDaysRotine,
												 CalendarUser calendar){
		
		List<DayRoutineDAO> dayRoutineDAOs = new ArrayList<DayRoutineDAO>();
		List<Date> scheduledDaysSorted = scheduledDays.stream()
					  .sorted(Comparator.comparingLong(Date::getTime))
					  .collect(Collectors.toList());
		Integer dayPos = 1;
		for(Date day: scheduledDaysSorted) {
			String estadoDeAnimo = calculateEstadoDeAnimo(calendar.getDayAndOpinion(),day);
			Integer pos = this.numberRoutineCorresponding(dayPos,numberDaysRotine);
			DayRoutineDAO diaDeRutina = new DayRoutineDAO(day,calendar.getRoutine(), pos, calendar.getId(), estadoDeAnimo);
			dayRoutineDAOs.add(diaDeRutina);
			dayPos = dayPos + 1;
		}
		
		return dayRoutineDAOs;
	}
	
	private String calculateEstadoDeAnimo(List<DayAndOpinion> dayAndOpinions, Date day) {
		String estadoDeAnimo = "";
		for(DayAndOpinion dayAndOpinion : dayAndOpinions ) {
			Date dayToOpinion = dayAndOpinion.getDayOpinon();
			if(dayToOpinion.getDate() == day.getDate()) {
				return dayAndOpinion.getOpinion();
			}
		}
		return estadoDeAnimo;
	}

	/*--------------------------------------------------------------------
	 	Descripción: Calcula el dia de la rutina, dependiendo del dia del calendio 
	 				recibido por por parametro(dayPos).
		Fecha: 		11/05/2022
	--------------------------------------------------------------------*/
	private Integer numberRoutineCorresponding(Integer dayPos, List<Integer> numberDaysRotine) {
		// Si la rutina es de un dia retorna retorno ese mismo dia.
		if(numberDaysRotine.size() == 1) {
			return numberDaysRotine.get(0);
		}
		
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

	public CalendarUser setOpinionRoutine(Integer calendarId, String opinon) {
		CalendarUser calendarUser = this.calendarUserRepository.findById(calendarId).get();
		calendarUser.addOpinion(opinon);
		return save(calendarUser);
		
	}
	
}
