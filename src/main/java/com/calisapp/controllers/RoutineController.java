package com.calisapp.controllers;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import com.calisapp.model.Routine;
import com.calisapp.services.RoutineService;

@RestController
@EnableAutoConfiguration
@CrossOrigin(origins = {"https://calisapp2.000webhostapp.com", "http://localhost:4200"},maxAge = 3600)
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
    public ResponseEntity<?> allRoutinesOfUser(@PathVariable("idUser") Long idUser) {
    	List<Routine> list = routineService.findWithUserId(idUser);

        return ResponseEntity.ok().body(list);
    }
    
    @PostMapping("/api/createRoutine")
    public ResponseEntity<?> createRouitne(@Validated 
									@RequestParam ("userId") Long userId,
									@RequestParam ("nameRoutine") String nameRoutine,
					    			@RequestParam ("excersices") List<Integer> excersices,
					    			@RequestParam ("daysRoutine") List<Integer> daysRoutine,
    								@RequestParam ("weeksRoutine") Integer weeksRoutine) {

    	Routine newRoutine = routineService.createRoutine(userId, nameRoutine, excersices, daysRoutine, weeksRoutine);
    	
        return ResponseEntity.ok().body(newRoutine);
    }
    
    @PutMapping("/api/editRoutine/{id}")
    public ResponseEntity<?> editRoutine(@Valid @PathVariable("id") Integer id,
							@RequestParam ("nameRoutine") String nameRoutine,
							@RequestParam ("excersices") List<Integer> excersices) {
    		
    	Routine routineUpdate = routineService.editRoutine(id, nameRoutine, excersices);
    	
        return ResponseEntity.ok().body(routineUpdate);
    }
    
    @PutMapping("/api/removeExerciseRoutine/{id}")
    public ResponseEntity<?> removeExerciseToRoutine(@Valid @PathVariable("id") Integer id,
							@RequestParam ("idExcersice") Integer idExcersice) {

    	Routine routineUpdate = routineService.removeExercise(id, idExcersice);
    	
        return ResponseEntity.ok().body(routineUpdate);
    }
    
    @PutMapping("/api/addExerciseRoutine/{id}")
    public ResponseEntity<?> addExerciseToRoutine(@Valid @PathVariable("id") Integer id,
							@RequestParam ("idExcersice") Integer idExcersice) {

    	Routine routineUpdate = routineService.addExercise(id, idExcersice);
    	
        return ResponseEntity.ok().body(routineUpdate);
    }
    
    @DeleteMapping("/api/deleteRoutine/{id}")
    public ResponseEntity<?> deleteRoutine(@PathVariable("id") Integer id,
    						@RequestParam ("idUser") Integer idUser) {
    	
        Routine routine = routineService.findByID(id);
    	routineService.deleteById(routine.getId(),idUser);
    	
    	return ResponseEntity.ok().body("Routine deleted with success!");	
    }
	
    @PutMapping("/api/generateOpinion")
    public ResponseEntity<?> generateOpinion(@Validated 
									@RequestParam ("routineId") Integer idRoutine,
									@RequestParam ("opinion") Integer opinion) {

    	Routine routineUpdate = routineService.generateOpinion(idRoutine, opinion);
    	
        return ResponseEntity.ok().body(routineUpdate);
    }
    
    @PostMapping("/api/createRoutineExercise")
    public ResponseEntity<?> createRouitneFromWithExercise(@Validated 
									@RequestParam ("userId") Long userId,
									@RequestParam ("nameRoutine") String nameRoutine,
					    			@RequestParam ("excersices") List<Integer> excersices,
					    			@RequestParam ("daysRoutine") List<Integer> daysRoutine,
    								@RequestParam ("weeksRoutine") Integer weeksRoutine,
    								@RequestBody Map<Integer,Integer> daysExercise) {

    	Routine newRoutine = routineService.createRouitneFromWithExercise(userId, nameRoutine, excersices, daysRoutine, weeksRoutine, daysExercise);
    	
        return ResponseEntity.ok().body(newRoutine);
    }
    
}