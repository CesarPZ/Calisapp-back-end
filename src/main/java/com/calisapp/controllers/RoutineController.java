package com.calisapp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.calisapp.model.Routine;
import com.calisapp.services.RoutineService;

@RestController
@EnableAutoConfiguration
public class RoutineController {

    @Autowired
    private RoutineService routineService;

    @GetMapping("/api/routines")
    public ResponseEntity<?> allLocations() {
    	List<Routine> list = routineService.findAll();

        return ResponseEntity.ok().body(list);
    } 
}