package com.calisapp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.calisapp.model.ProcessExercise;
import com.calisapp.services.ProcessExerciseService;

@RestController
@EnableAutoConfiguration
@CrossOrigin(origins = {"https://calisapp2.000webhostapp.com", "http://localhost:4200"},maxAge = 3600)
public class ProcessExerciseController {

    @Autowired
    private ProcessExerciseService processExerciseService;

    @GetMapping("/api/processExercise")
    public ResponseEntity<?> allProcessExercise() {
    	List<ProcessExercise> list = processExerciseService.findAll();

        return ResponseEntity.ok().body(list);
    } 
}
