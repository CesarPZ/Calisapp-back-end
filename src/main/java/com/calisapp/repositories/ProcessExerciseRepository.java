package com.calisapp.repositories;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.calisapp.model.ProcessExercise;

@Configuration
@Repository
public interface ProcessExerciseRepository extends CrudRepository<ProcessExercise, Integer> {
	
		List<ProcessExercise> findAll();
}
