package com.calisapp.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.calisapp.CalisappApplication;
import com.calisapp.exceptions.ResourceNotFoundException;
import com.calisapp.model.User;
import com.calisapp.repositories.UserRepository;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes=CalisappApplication.class)
@ImportResource("applicationContext.xml")
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;
    
    private User createUser() {
        User user = new User();
        user.setName("Alex");
        user.setMail("alex@gmail.com");
        user.setPassword("1234");

        return user;
    }
    
    @Test
    public void retrieveUserfindByIdTest() throws Exception {
    	Optional<User> user = Optional.of(createUser());
        when(userRepository.findById(1L)).thenReturn(user);
      
	    assertThat(userService.findByID(1L).getMail()).isEqualTo("alex@gmail.com");
	    assertThat(userService.findByID(1L).getName()).contains("Alex");
	    assertThat(userService.findByID(1L).getPassword()).isEqualTo("1234");
    }
    
    @Test 
	public void userfindByIDException() throws Exception {
    	Long id = 1L;
        
        ResourceNotFoundException exception = Assertions.assertThrows(ResourceNotFoundException.class, () -> {
        	userService.findByID(1L);
		});	
	    
		assertEquals("User with ID:"+id+" Not Found!", exception.getMessage());
	}
    
    @Test
    public void retrieveUserfindByMailTest() throws Exception {
    	Optional<User> user = Optional.of(createUser());
        when(userRepository.findByMail("alex@gmail.com")).thenReturn(user);

	    assertThat(userService.findByMail("alex@gmail.com").getMail()).isEqualTo(user.get().getMail());
	    assertThat(userService.findByMail("alex@gmail.com").getName()).contains("Alex");
	    assertThat(userService.findByMail("alex@gmail.com").getPassword()).isEqualTo(user.get().getPassword());
    }
    
    @Test
    public void findAllUsersTest() throws Exception {
        User user = new User("Esteban", "esteban@spring.io", "1234");
        User user2 = new User("duke", "duke@spring.io", "1234");

    	List<User> allUsers = Arrays.asList(user, user2);
		when(userRepository.findAll()).thenReturn(allUsers);
    	
	    assertThat(userService.findAll().size()).isEqualTo(2);
    }
    
    @Test
    public void findAllUsersIsEmptyTest() throws Exception {
    	List<User> allUsers = Arrays.asList();
    	
		when(userRepository.findAll()).thenReturn(allUsers);
    	
	    assertThat(userService.findAll().size()).isEqualTo(0);
    }
    
    @Test
    public void deleteUserByIdtest() throws Exception {
    	User user = new User("Esteban", "esteban@spring.io", "1234");

        userService.save(user);
        userService.deleteById(user.getId());

        Optional<User> optional = userRepository.findById(user.getId());

        assertEquals(Optional.empty(), optional);
        verify(userRepository, times(1)).deleteById(user.getId());   
    }
    
    @Test
    public void saveUserTest(){
    	User user = new User("Esteban", "esteban@spring.io", "1234");

    	userService.save(user);
        
        ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);
        
        verify(userRepository).save(captor.capture());
        assertThat(captor.getValue().getName().equals(user.getName()));
    }
    
    @Test
    public void updateUserTest() throws Exception{
    	Optional<User> user = Optional.of(createUser());
        when(userRepository.findById(1L)).thenReturn(user);
        
	    assertThat(userService.findByID(1L).getName()).isEqualTo("Alex");

        userService.update(1L, "newName", "newPassword");
                
        ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);
           
        verify(userRepository).save(captor.capture());
        assertThat(captor.getValue().getName().equals(user.get().getName()));
	    assertThat(userService.findByID(1L).getName()).isEqualTo("newName");
    }    
    
    @Test 
	public void updateUserExceptionByShortName() throws Exception {
    	Optional<User> user = Optional.of(createUser());
        when(userRepository.findById(1L)).thenReturn(user);
        
	    assertThat(userService.findByID(1L).getName()).isEqualTo("Alex");
        
        ResourceNotFoundException exception = Assertions.assertThrows(ResourceNotFoundException.class, () -> {
        	userService.update(1L, "new", "newPassword");
		});	
	    
		assertEquals("Name must be longer than 4 characters", exception.getMessage());
	}
    
    @Test 
	public void updateUserExceptionByShortPassword() throws Exception {
    	Optional<User> user = Optional.of(createUser());
        when(userRepository.findById(1L)).thenReturn(user);
        
	    assertThat(userService.findByID(1L).getName()).isEqualTo("Alex");
        
        ResourceNotFoundException exception = Assertions.assertThrows(ResourceNotFoundException.class, () -> {
        	userService.update(1L, "newName", "pas");
		});	
	    
		assertEquals("Password must be longer than 4 characters", exception.getMessage());
	}
    
    @Test
    public void registerUsertest() throws Exception {
        userService.register("alex", "alex@gmail.com", "1234");

        ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);

        verify(userRepository).save(captor.capture());
    }
    
    @Test 
	public void notRegisterUserByMailInvalid() throws Exception {       
        ResourceNotFoundException exception = Assertions.assertThrows(ResourceNotFoundException.class, () -> {
        	userService.register("alex", "alex.com", "1234");
		});	
	    
		assertEquals("Access denied: Mail not valid", exception.getMessage());
	}
    
    @Test
    public void loginUsertest() throws Exception {
    	Optional<User> user = Optional.of(createUser());

        when(userRepository.findByMail("alex@gmail.com")).thenReturn(user);

        User userLogged =userService.login("alex@gmail.com", "1234");
        
	    assertThat(userService.findByMail("alex@gmail.com").getMail()).isEqualTo(userLogged.getMail());
    }
    
    @Test
    public void notloginUserByUserNotExistTest() throws Exception {
        ResourceNotFoundException exception = Assertions.assertThrows(ResourceNotFoundException.class, () -> {
        	userService.login("alex@gmail.com", "1234");
		});	
		assertEquals("Access denied: User not exist", exception.getMessage());
    }
    
    @Test
    public void notloginUserByIncorrectPasswordTest() throws Exception {
    	Optional<User> user = Optional.of(createUser());

        when(userRepository.findByMail("alex@gmail.com")).thenReturn(user);

        ResourceNotFoundException exception = Assertions.assertThrows(ResourceNotFoundException.class, () -> {
        	userService.login("alex@gmail.com", "123456");
		});	
		assertEquals("Access denied: Incorrect password", exception.getMessage());
    }
}
