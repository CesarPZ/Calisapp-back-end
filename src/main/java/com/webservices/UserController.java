package com.webservices;

import java.util.List;
import java.util.NoSuchElementException;

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

import com.exceptions.ResourceNotFoundException;
import com.model.User;
import com.servicies.UserService;

@CrossOrigin(origins = "http://localhost:4200",maxAge = 3600)
@RestController
@EnableAutoConfiguration
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
    	try {
    		User user = userService.findByID(id);
    		return ResponseEntity.ok().body(user);
        
    	} catch (NoSuchElementException e){

    		throw new ResourceNotFoundException("User with ID:"+id+" Not Found!");
    	}    	   
    }
    
    @DeleteMapping(value="/api/users/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable("id") Long id) {
    	try {
    		User user = userService.findByID(id);
        
    		userService.deleteById(user.getId());

    		return ResponseEntity.ok().body("User deleted with success!");	
        
    	} catch (NoSuchElementException e){
    		throw new ResourceNotFoundException("User with ID:"+id+" Not Found!");
    		}
    }
    
    @PostMapping("/api/users/login")
	public ResponseEntity<User> login(@RequestParam ("mail") String mail,
									  @RequestParam ("password") String password) {	
		
		User userLogin = userService.login(mail, password);

		return ResponseEntity.ok().body(userLogin);	
	}
    	
    @PostMapping(path="/api/users/register")
	public ResponseEntity<User> register(@RequestParam ("name") String name,
										 @RequestParam ("mail") String mail,
										 @RequestParam ("password") String password) throws Exception {
		
		User userRegistrate = userService.register(name, mail, password);
		
		return ResponseEntity.ok().body(userRegistrate);	
	}	
    
    @PutMapping("/api/users/{id}")
	public ResponseEntity<User> updateUserById(@PathVariable("id") Long id, 
			@RequestParam (value = "name", required=false) String name,
			@RequestParam (value = "password", required=false) String password) {
		
		User userUpdate = userService.update(id,name,password);

		return ResponseEntity.ok().body(userUpdate);	
	}	
}
