package com.calisapp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.calisapp.model.Routine;
import com.calisapp.services.RoutineService;

@RestController
@EnableAutoConfiguration
@CrossOrigin(origins = "http://localhost:4200",maxAge = 3600)
public class RoutineController {

    @Autowired
    private RoutineService routineService;

    @GetMapping("/api/routines")
    public ResponseEntity<?> allRoutines() {
    	List<Routine> list = routineService.findAll();

        return ResponseEntity.ok().body(list);
    }
    
    @GetMapping("/api/routine/{level}")
    public ResponseEntity<?> allRoutinesOfAppWithLevel(@PathVariable("level") String level) {
    	List<Routine> list = routineService.findWithLevel(level);

        return ResponseEntity.ok().body(list);
    }
    
    @GetMapping("/api/routineUser/{idUser}")
    public ResponseEntity<?> allRoutinesOfUser(@PathVariable("idUser") Integer idUser) {
    	List<Routine> list = routineService.findWithUserId(idUser);

        return ResponseEntity.ok().body(list);
    }
    
    @PostMapping("/api/createRoutine")
    public ResponseEntity<?> createRouitne(@Validated 
									@RequestParam ("userId") Long userId,
									@RequestParam ("nameRoutine")  String nameRoutine,
					    			@RequestParam ("excersices") List<Integer> excersices) {

    	Routine newRoutine = routineService.createRoutine(userId, nameRoutine, excersices);
    	
        return ResponseEntity.ok().body(newRoutine);
    }
    
}