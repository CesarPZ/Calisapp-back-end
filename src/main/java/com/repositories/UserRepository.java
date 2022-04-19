package com.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.model.User;

@Configuration
@Repository
public interface UserRepository extends CrudRepository<User, Long> {
	Optional<User> findById(Integer id);
	
	List<User> findAll();

	Optional<User> findByMail(String name);
}
