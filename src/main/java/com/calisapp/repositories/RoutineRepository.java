package com.calisapp.repositories;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.calisapp.model.Routine;

@Configuration
@Repository
public interface RoutineRepository extends CrudRepository<Routine, Integer> {

	List<Routine> findAll();
}