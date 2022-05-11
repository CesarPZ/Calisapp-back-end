package com.calisapp.repositories;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.calisapp.model.CalendarUser;

@Configuration
@Repository
public interface CalendarUserRepository extends CrudRepository<CalendarUser, Integer> {

	@Query(value = "SELECT * FROM calendar_user r WHERE r.id in (SELECT calendar_id FROM users_calendar u WHERE u.user_id = :idUser)", nativeQuery = true)
	List<CalendarUser> findWithUserId(@Param("idUser") Integer idUser);
	
}
