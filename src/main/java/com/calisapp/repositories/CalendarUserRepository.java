package com.calisapp.repositories;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.calisapp.model.CalendarUser;
import com.calisapp.model.DayAndOpinion;

@Configuration
@Repository
public interface CalendarUserRepository extends CrudRepository<CalendarUser, Long> {

	@Query(value = "SELECT * FROM calendar_user r WHERE r.id in (SELECT calendar_id FROM users_calendar u WHERE u.user_id = :idUser)", nativeQuery = true)
	List<CalendarUser> findWithUserId(@Param("idUser") Long idUser);

	Optional<CalendarUser> findById(Integer calendarId);

}
