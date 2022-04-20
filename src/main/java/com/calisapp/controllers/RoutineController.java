package com.calisapp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.calisapp.model.Routine;
import com.calisapp.services.RoutineService;

@RestController
@EnableAutoConfiguration
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
  /*  
    @PostMapping("/api/routine")
    public ResponseEntity<?> createRutine(@Validated 
					    		@RequestParam ("userId") Integer userId,
								@RequestParam ("projectId") Integer projectId,
								@RequestParam ("amount") Integer amount,
							    @RequestParam ("comment") String comment){
		
    	Routine newRoutine = routineService.createRutine(userId, projectId, amount, comment);

		return ResponseEntity.ok().body(newRoutine);
    }*/
}