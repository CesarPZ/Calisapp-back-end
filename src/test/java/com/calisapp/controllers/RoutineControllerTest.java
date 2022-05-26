package com.calisapp.controllers;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.calisapp.CalisappApplication;
import com.calisapp.model.Exercise;
import com.calisapp.model.Routine;
import com.calisapp.model.RoutineByLevel;
import com.calisapp.model.RoutineOfUser;
import com.calisapp.model.User;
import com.calisapp.services.RoutineService;

@RunWith(SpringRunner.class)
@WebMvcTest(RoutineController.class)
@ContextConfiguration(classes=CalisappApplication.class)
public class RoutineControllerTest {
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@Autowired
    private MockMvc mvc;
	
    @MockBean
    private RoutineService routineService;
    
    static RoutineByLevel routineByLevel;
	static RoutineOfUser routineOfUser;
	
	static Exercise ejercicio1;
	static Exercise ejercicio2;
	static Exercise ejercicio3;
	
	static User user=mock(User.class);
		
    @Before
	public void setup() {
    	mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    	
    	ejercicio1 = new Exercise.ExerciseBuilder().build();
		ejercicio2 = new Exercise.ExerciseBuilder().build();
		ejercicio3 = new Exercise.ExerciseBuilder().build();
				
		Set<Exercise> ejerciciosRoutineByLevel = new HashSet<Exercise>();
		ejerciciosRoutineByLevel.add(ejercicio1);
		ejerciciosRoutineByLevel.add(ejercicio2);
		
		Set<Exercise> ejerciciosRoutineOfUser = new HashSet<Exercise>();
		ejerciciosRoutineOfUser.add(ejercicio3);
		
		routineByLevel = new RoutineByLevel.RoutineByLevelBuilder()
							.withNameRoutine("rutina1")
							.withExercises(ejerciciosRoutineByLevel)
							.withLevel("avanzado")
							.build();
		
		routineOfUser = new RoutineOfUser.RoutineOfUserBuilder()
							.withNameRoutine("Intermedio")
							.withExercises(ejerciciosRoutineOfUser)
							.build();		
	}
        
    @Test
    public void findAllRoutineTest() throws Exception {
    	RoutineOfUser routine = new RoutineOfUser ("Rutina Vieja");
    	RoutineOfUser routine2 = new RoutineOfUser ("Rutina Nueva");

    	List<Routine> allRoutine = Arrays.asList(routine, routine2);
    	
		when(routineService.findAll()).thenReturn(allRoutine);
    	
    	this.mvc
        .perform(MockMvcRequestBuilders.get("/api/routines"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(2))
        .andExpect(MockMvcResultMatchers.jsonPath("$[0].nameRoutine").value("Rutina Vieja"))
        .andExpect(MockMvcResultMatchers.jsonPath("$[1].nameRoutine").value("Rutina Nueva"));
    	
        verify(routineService, times(1)).findAll();        
    }
    
    @Test
    public void findAllRoutinesOfAppWithLevelTest() throws Exception {
        Routine routine2 = new RoutineByLevel("Rutina text", "avanzado");
    	
        List<Routine> allRoutineWithLevel = Arrays.asList(routineByLevel, routine2);

		when(routineService.findWithLevel("avanzado")).thenReturn(allRoutineWithLevel);
    	
    	this.mvc
        .perform(MockMvcRequestBuilders.get("/api/routine/avanzado"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(2))
        .andExpect(MockMvcResultMatchers.jsonPath("$[0].nameRoutine").value("rutina1"))
        .andExpect(MockMvcResultMatchers.jsonPath("$[1].nameRoutine").value("Rutina text"));
    } 
    
    @Test
    public void findAllRoutinesOfUserTest() throws Exception { 
    	List<Routine> allRoutine = new ArrayList<Routine>();
    	allRoutine.add(routineByLevel);
    	allRoutine.add(routineOfUser);
		
    	Long id = user.getId();
    	
		when(routineService.findWithUserId(id)).thenReturn(allRoutine);
    	
    	this.mvc
        .perform(MockMvcRequestBuilders.get("/api/routineUser/"+id))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(2))
        .andExpect(MockMvcResultMatchers.jsonPath("$[0].nameRoutine").value("rutina1"))
        .andExpect(MockMvcResultMatchers.jsonPath("$[1].nameRoutine").value("Intermedio"));
    }    
}
