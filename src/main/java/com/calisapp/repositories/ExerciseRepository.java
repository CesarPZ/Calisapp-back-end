package com.calisapp.repositories;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.calisapp.model.Exercise;


@Configuration
@Repository
public interface ExerciseRepository extends CrudRepository<Exercise, Integer> {

	List<Exercise> findAll();
}
