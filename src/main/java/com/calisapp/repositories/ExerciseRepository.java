package com.calisapp.repositories;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

import com.calisapp.model.Exercise;

@Configuration
@Repository
public interface ExerciseRepository extends CrudRepository<Exercise, Integer> {

	List<Exercise> findAll();
	
	@Query(value = "SELECT * FROM exercise e WHERE e.id in (SELECT exercises_id FROM routines_exercises r WHERE r.routine_id = :idRoutine)", nativeQuery = true)
	List<Exercise> findExcersiteToRoutine(@Param("idRoutine") String idRoutine);
}
