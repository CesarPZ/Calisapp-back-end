package com.calisapp.repositories;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.calisapp.model.Routine;

@Configuration
@Repository
public interface RoutineRepository extends CrudRepository<Routine, Integer> {

	List<Routine> findAll();
	
	@Query(value =  "SELECT * FROM routines r WHERE r.generated_by = :generated_by and r.level = :level", nativeQuery = true)
	List<Routine> findWithLevel(@Param("level") String level, @Param("generated_by") String generated_by);
	
	//@Query(value = "SELECT * FROM routines r WHERE r.id in (SELECT routines_id FROM user_routines u WHERE u.routines_id = :idUser)", nativeQuery = true)
	
	@Query(value = "SELECT * FROM routines r WHERE r.id in (SELECT routines_id FROM user_routines u WHERE u.user_id = :idUser)", nativeQuery = true)
	List<Routine> findWithUserId(@Param("idUser") Integer idUser);
}
