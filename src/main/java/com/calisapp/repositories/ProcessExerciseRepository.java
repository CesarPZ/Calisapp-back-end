package com.calisapp.repositories;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.calisapp.model.Exercise;
import com.calisapp.model.ProcessExercise;

@Configuration
@Repository
public interface ProcessExerciseRepository extends CrudRepository<ProcessExercise, Integer> {
	
		List<ProcessExercise> findAll();
		
	/*	@Query(value = "SELECT * FROM processexercise e WHERE e.id in (SELECT exercises_id FROM routines_exercises r WHERE r.routine_id = :idExercise)", nativeQuery = true)
		List<Exercise> findExcersiteToRoutine(@Param("idExercise") String idExercise);*/
}
