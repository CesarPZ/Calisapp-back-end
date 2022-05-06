package com.calisapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
