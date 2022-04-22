package com.calisapp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.calisapp.model.Exercise;
import com.calisapp.services.ExerciseService;

@RestController
@EnableAutoConfiguration
@CrossOrigin(origins = "http://localhost:4200",maxAge = 3600)
public class ExerciseController {

    @Autowired
    private ExerciseService exerciseService;

    @GetMapping("/api/exercises")
    public ResponseEntity<?> allLExercise() {
    	List<Exercise> list = exerciseService.findAll();

        return ResponseEntity.ok().body(list);
    } 
    
    @GetMapping("/api/exercisesRoutine/{idRoutine}")
    public ResponseEntity<?> allLExerciseByRoutine(@PathVariable("idRoutine") String idRoutine) {
    	List<Exercise> list = exerciseService.findExerciseByRoutine(idRoutine);

        return ResponseEntity.ok().body(list);
    }
}
