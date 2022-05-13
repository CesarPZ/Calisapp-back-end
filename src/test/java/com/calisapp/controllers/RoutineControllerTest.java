package com.calisapp.controllers;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

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
import com.calisapp.model.Routine;
import com.calisapp.model.RoutineOfUser;
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
         
    @Before
	public void setup() {
    	mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
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
}
