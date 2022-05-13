package com.calisapp.controllers;

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
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.calisapp.CalisappApplication;
import com.calisapp.model.User;
import com.calisapp.services.UserService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
@ContextConfiguration(classes=CalisappApplication.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest {
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@Autowired
    private MockMvc mvc;

    @MockBean
    private UserService userService;
         
    @Before
	public void setup() {
    	mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
        
    @Test
    public void findAllUsersTest() throws Exception {
        User user = new User("duke", "duke@spring.io", "1234");

    	List<User> allUsers = Arrays.asList(user);
		when(userService.findAll()).thenReturn(allUsers);
    	
    	this.mvc
        .perform(MockMvcRequestBuilders.get("/api/users"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(1))
        .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("duke"))
        .andExpect(MockMvcResultMatchers.jsonPath("$[0].mail").value("duke@spring.io"));
    	
        verify(userService, times(1)).findAll();        
    }

    @Test
    public void getUserByIdTest() throws Exception {
        User user = new User("duke", "duke@spring.io", "1234");

		when(userService.findByID(1L)).thenReturn(user);
    	
    	this.mvc
        .perform(MockMvcRequestBuilders.get("/api/users/1"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("duke"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.mail").value("duke@spring.io"));
    } 
    
    @Test
    public void deleteUserByIdtest() throws Exception {
       String uri = "/api/users/3";
       User user = new User("duke", "duke@spring.io", "1234");

	   when(userService.findByID(3L)).thenReturn(user);

       MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
       int status = mvcResult.getResponse().getStatus();
       String content = mvcResult.getResponse().getContentAsString();

       assertEquals(200, status);
       assertEquals(content, "User deleted with success!");
    }
    
    @Test
    public void loginUserTest() throws Exception {
        User user = new User("duke", "duke@spring.io", "1234");
        String uri = "/api/users/login?mail="+user.getMail()+"&password="+user.getPassword();

//		when(userService.login("duke@spring.io", "1234")).thenReturn(user);
    		
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders
        		.post(uri))
        		.andReturn();

        int status = mvcResult.getResponse().getStatus();
        
        assertEquals(200, status);
        verify(userService, times(1)).login("duke@spring.io", "1234");        
    }
    
    @Test
    public void registerUserTest() throws Exception {
        User user = new User("duke", "duke@spring.io", "1234");
        String uri = "/api/users/register?name="+user.getName()+"&mail="+user.getMail()+"&password="+user.getPassword();

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders
        		.post(uri))
        		.andReturn();

        int status = mvcResult.getResponse().getStatus();
        
        assertEquals(200, status);
        verify(userService, times(1)).register("duke", "duke@spring.io", "1234");        
    }
  
    @Test
    public void updateUserTest() throws Exception {
    	Long id = 5L;
    	String newName = "pepe";
    	String newPassword = "12345678";
    		
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders
        		.put("/api/users/"+id+"?name="+newName+"&password="+newPassword))
        		.andReturn();        
        
        int status = mvcResult.getResponse().getStatus();
        
        assertEquals(200, status);
        verify(userService, times(1)).update(id, "pepe", "12345678");        
    }
}




