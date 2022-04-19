package com.calisapp.webservices;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.calisapp.model.Exercise;
import com.calisapp.services.ExerciseService;


@RestController
@EnableAutoConfiguration
public class ExerciseController {

    @Autowired
    private ExerciseService exerciseService;

    @GetMapping("/api/exercises")
    public ResponseEntity<?> allLocations() {
    	List<Exercise> list = exerciseService.findAll();

        return ResponseEntity.ok().body(list);
    } 
}
