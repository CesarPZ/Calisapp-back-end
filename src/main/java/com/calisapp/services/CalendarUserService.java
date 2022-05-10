package com.calisapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.calisapp.model.CalendarUser;
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

	public List<CalendarUser> findWithUserId(Integer idUser) {
		return this.calendarUserRepository.findWithUserId(idUser);
	}
	
	@Transactional
	public CalendarUser save(CalendarUser model) {
		return calendarUserRepository.save(model);
	}
}
