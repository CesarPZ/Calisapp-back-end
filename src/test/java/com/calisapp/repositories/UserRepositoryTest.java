package com.calisapp.repositories;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.calisapp.model.User;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest  {
  @Autowired
  private TestEntityManager entityManager;
  
  @Autowired
  private UserRepository userRepository;
  
  @Before
  public void setup() {
      userRepository.deleteAll();
  }
  
  @Test
  public void saveUserInRepositorySuccessfullyTest() {
	  User user = new User("alex", "alex@gmail.com", "1234");
  
	  entityManager.persistAndFlush(user);  
    
	  assertThat(user).hasFieldOrPropertyWithValue("name", "alex");
	  assertThat(user).hasFieldOrPropertyWithValue("mail", "alex@gmail.com");
	  assertThat(user).hasFieldOrPropertyWithValue("password", "1234");
  }
  
  @Test
  public void itShouldFindUserByMailTest() {   
	  User user = userRepository.save(new User("alex", "alex@gmail.com", "1234"));  
	  
      Optional<User> found = userRepository.findByMail("alex@gmail.com");

      assertThat(found.get().getMail()).isEqualTo(user.getMail());
  }
  
  @Test
  public void whenFindByIdThenReturnUserTest() {
	  User user = userRepository.save(new User("alex", "ale@gmail.com", "1234"));  

      assertThat(userRepository.findById(user.getId()).get()).isEqualTo(user);
  }
  
  @Test
  public void findAllUsersTest() {
	  User user = new User("alex", "ale@gmail.com", "1234");  
	  User user2 =new User("esteban", "esteban@gmail.com", "1234");   

	  userRepository.save(user);
	  userRepository.save(user2);

	  List<User> allUsers = (List<User>) userRepository.findAll();
	  assertEquals("esteban", allUsers.get(1).getName());
	  assertEquals("ale@gmail.com", allUsers.get(0).getMail());
  }
    
  @Test
  public void givenIdTODeleteThenShouldDeleteUserTest() {
        User user = new User("alex", "ale@gmail.com", "1234");
        userRepository.save(user);
        userRepository.deleteById(user.getId());
        
        Optional<User> optional = userRepository.findById(user.getId());
        
        assertEquals(Optional.empty(), optional);
  }
}