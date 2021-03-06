package com.calisapp.services;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.calisapp.daos.DayRoutineDAO;
import com.calisapp.exceptions.ResourceNotFoundException;
import com.calisapp.model.CalendarUser;
import com.calisapp.model.User;
import com.calisapp.repositories.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository  repository;
	@Autowired
	private CalendarUserService calendarUserService;
	
	@Transactional
	public User save(User model) {		
		User user = null;
    	try {
    		user = findByMail(model.getMail());
    		
    	} catch (Exception e){}
    	if(user!= null) {
    		throw new ResourceNotFoundException("Access denied: User already exist");
    	}
    		return repository.save(model);	
	}

	public User findByID(Long id) {
		try{
			return this.repository.findById(id).get();
			
		}catch (NoSuchElementException e){
    		throw new ResourceNotFoundException("User with ID:"+id+" Not Found!");
    	}
	}

	public List<User> findAll() {
		return this.repository.findAll();
	}
	
	public User findByMail(String mail) {
		return this.repository.findByMail(mail).get();
	}
	
	public void deleteById(Long id) {
		this.repository.deleteById(id);		
	}

	@Transactional
	public User update(Long id, String name, String password, String mobileNumber) throws Exception {
		User user = this.findByID(id);		
		validateNameAndPassword(name, password);
		
		user.setName(name);
		user.setPassword(password);
		user.setMobileNumber(mobileNumber);
		
		return this.repository.save(user);
	}
	
    public User login(String mail, String pass) {
    	User user = null;
    	try {
    		user = findByMail(mail);
    		
    	} catch (Exception e){
    		throw new ResourceNotFoundException("Access denied: User not exist");
    	}
    	if(!user.getPassword().equals(pass)) {
    		throw new ResourceNotFoundException("Access denied: Incorrect password");
    	}
	
    	return user;
    }

	public User register(String name, String mail, String password) throws Exception {
		validateMail(mail);
		validateNameAndPassword(name, password);

		User newUser = new User(name, mail, password);
				
		return save(newUser);
	}
	
	public void validateMail(String mail) {
		try {
			String isValidEmail = "^(?=.{1,64}@)[\\p{L}0-9_-]+(\\.[\\p{L}0-9_-]+)*@"
		            + "[^-][\\p{L}0-9-]+(\\.[\\p{L}0-9-]+)*(\\.[\\p{L}]{2,})$";
			
			Pattern pattern = Pattern.compile(isValidEmail);
			if (!pattern.matcher(mail).matches())
				throw new Exception();
    		
    	} catch (Exception e){
    		throw new ResourceNotFoundException("Access denied: Mail not valid");
    	}			
	}
	
	public void validateNameAndPassword(String name, String password) throws Exception {
		if(name == null || name.length() < 4) {
			throw new ResourceNotFoundException("Name must be longer than 4 characters");
		}
		if(name == null || password.length() < 4) {
			throw new ResourceNotFoundException("Password must be longer than 4 characters");
		}
	}

	public Set<User> getUserWithRoutineToday() {
		Set<User> userWhatsApp = new HashSet<User>();
		List<User> users = this.repository.findAll();
		for(User user: users) {
			//if(user.getMobileNumber() != null  && user.getMobileNumber() != "") {
				List<CalendarUser> allDaysRoutines = calendarUserService.findWithUserIdToday(user.getId());
				if(!allDaysRoutines.isEmpty()) {
					userWhatsApp.add(user);
				}
			//}
		}
		
		return userWhatsApp;
	}

	public List<CalendarUser> userHaveRoutineToday(Long userId) {
		List<CalendarUser> allDaysRoutines = calendarUserService.findWithUserIdToday(userId);

		return allDaysRoutines;
	}
	
}
