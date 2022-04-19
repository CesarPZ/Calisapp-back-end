package com.calisapp.services;

import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.calisapp.model.User;
import com.calisapp.repositories.UserRepository;
import com.exceptions.ResourceNotFoundException;

@Service
public class UserService {
	@Autowired
	private UserRepository  repository;
	
	@Transactional
	public User save(User model) {
		return repository.save(model);
		
		/*
		User user = null;
    	try {
    		user = findByMail(model.getMail());
    		
    	} catch (Exception e){}
    	if(user!= null) {
    		throw new ResourceNotFoundException("Access denied: User already exist");
    	}
    		return repository.save(model);
    	*/	
	}

	public User findByID(Long id) {
		return this.repository.findById(id).get();
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
	public User update(Long id, String name, String password) {
		User user = this.repository.findById(id).get();
		if(name != null) {
			user.setName(name);
		}
		if(password != null) {
			user.setPassword(password);
		}
		
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
}
