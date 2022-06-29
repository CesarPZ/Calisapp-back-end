package com.calisapp.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.calisapp.daos.DayRoutineDAO;
import com.calisapp.model.CalendarUser;
import com.calisapp.model.User;
import com.calisapp.services.UserService;

@RestController
@EnableAutoConfiguration
@CrossOrigin(origins = "http://localhost:4200",maxAge = 3600)
public class UserController {
	@Autowired
	private UserService userService;
	
	@GetMapping("/api/users")
    public ResponseEntity<?> allUsers() {
        List<User> list = userService.findAll();

        return ResponseEntity.ok().body(list);
    }
	
    @GetMapping("/api/users/{id}")
    public ResponseEntity<?> getUserById(@PathVariable("id") Long id) {
    	User user = userService.findByID(id);
    	
    	return ResponseEntity.ok().body(user);
    }
    
    @DeleteMapping(value="/api/users/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable("id") Long id) {
    	User user = userService.findByID(id);
		userService.deleteById(user.getId());

    	return ResponseEntity.ok().body("User deleted with success!");
    }
    
    @PostMapping("/api/users/login")
	public ResponseEntity<User> login(@Valid @RequestParam ("mail") String mail,
									  @RequestParam ("password") String password) {	
		
		User userLogin = userService.login(mail, password);

		return ResponseEntity.ok().body(userLogin);	
	}
    	
    @PostMapping(path="/api/users/register")
	public ResponseEntity<User> register(@Valid @RequestParam ("name") String name,
										 @RequestParam ("mail") String mail,
										 @RequestParam ("password") String password) throws Exception {
		
		User userRegistrate = userService.register(name, mail, password);
		
		return ResponseEntity.ok().body(userRegistrate);	
	}	
    
    @PutMapping("/api/users/{id}")
	public ResponseEntity<User> updateUserById(@Valid @PathVariable("id") Long id, 
											@RequestParam(value="name", required=false) String name,
											@RequestParam(value="password", required=false) String password) throws Exception {
		
		User userUpdate = userService.update(id,name,password);

		return ResponseEntity.ok().body(userUpdate);	
	}
    
    @GetMapping("/api/routinesToday/{idUser}")
    public ResponseEntity<?> allRoutinesOfUser(@PathVariable("idUser") Long idUser) {
    	List<CalendarUser> list = userService.userHaveRoutineToday(idUser);

        return ResponseEntity.ok().body(list);
    }
}
