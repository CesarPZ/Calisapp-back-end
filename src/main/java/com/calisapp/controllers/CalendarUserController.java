package com.calisapp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.calisapp.daos.DayRoutineDAO;
import com.calisapp.model.CalendarUser;
import com.calisapp.model.Exercise;
import com.calisapp.services.CalendarUserService;

@RestController
@EnableAutoConfiguration
@CrossOrigin(origins = "http://localhost:4200",maxAge = 3600)
public class CalendarUserController {

    @Autowired
    private CalendarUserService calendarUserService;

    @GetMapping("/api/calendarUser")
    public ResponseEntity<?> allLExercise() {
    	List<CalendarUser> list = calendarUserService.findAll();

        return ResponseEntity.ok().body(list);
    }
    
    @GetMapping("/api/calendarUser/{idUser}")
    public ResponseEntity<?> allRoutinesOfUser(@PathVariable("idUser") Long idUser) {
    	List<DayRoutineDAO> list = calendarUserService.findWithUserId(idUser);

        return ResponseEntity.ok().body(list);
    }
    
    @PutMapping("/api/addOpinion/{calendarId}")
    public ResponseEntity<?> addOpinionToRoutine(@PathVariable ("calendarId") Integer calendarId,
												@RequestParam ("opinon") String opinon) {
								
    	CalendarUser calendarUser = calendarUserService.setOpinionRoutine(calendarId, opinon);
    	
        return ResponseEntity.ok().body(calendarUser);
    }
    
}
