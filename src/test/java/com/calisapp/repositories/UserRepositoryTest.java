package com.calisapp.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

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
      
  @Test
  public void whenfindByMailthenReturnUserTest() {
      // given
      User alex = new User("alex", "alex@gmail.com", "1234");
      entityManager.persist(alex);
      entityManager.flush();

      // when
      Optional<User> found = userRepository.findByMail("alex@gmail.com");

      // then
      assertThat(found.get().getMail()).isEqualTo(alex.getMail());
  }
  
  @Test
  public void saveUserInRepositoryTest() {
    User user = userRepository.save(new User("alex", "ale@gmail.com", "1234"));
    
    assertThat(user).hasFieldOrPropertyWithValue("name", "alex");
    assertThat(user).hasFieldOrPropertyWithValue("mail", "ale@gmail.com");
    assertThat(user).hasFieldOrPropertyWithValue("password", "1234");
  }
}