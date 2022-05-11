package com.calisapp.services;

import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.calisapp.model.User;
import com.calisapp.repositories.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
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
      
        assert userService.findByID(1L).getMail().contains("alex@gmail.com");
        assert userService.findByID(1L).getName().contains("Alex");
        assert userService.findByID(1L).getPassword().contains("1234");
    }
    
    @Test
    public void retrieveUserfindByMailTest() throws Exception {
    	Optional<User> user = Optional.of(createUser());
        when(userRepository.findByMail("alex@gmail.com")).thenReturn(user);

        assert userService.findByMail("alex@gmail.com").getMail().equals(user.get().getMail());
        assert userService.findByMail("alex@gmail.com").getName().contains("Alex");
        assert userService.findByMail("alex@gmail.com").getPassword().contains("1234");
    }
    
    @Test
    public void findAllUsersTest() throws Exception {
    	User user = createUser();

    	List<User> allUsers = Arrays.asList(user);
		when(userRepository.findAll()).thenReturn(allUsers);
    	
	    assert userService.findAll().equals(allUsers);
    }
    
    @Test
    public void findAllUsersIsEmptyTest() throws Exception {
    	List<User> allUsers = Arrays.asList();
		when(userRepository.findAll()).thenReturn(allUsers);
    	
	    assert userService.findAll().isEmpty();
    }
}


