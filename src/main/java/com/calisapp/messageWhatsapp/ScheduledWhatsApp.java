package com.calisapp.messageWhatsapp;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.calisapp.model.User;
import com.calisapp.services.UserService;

@Configuration
@EnableScheduling
public class ScheduledWhatsApp {
	@Autowired
	private UserService userService;
	
	@Scheduled(cron = "0 1 18 * * ?", zone = "America/Buenos_Aires")
	public void scheduleTaskUsingCronExpression() {
		Set<User> whatsAppUserRoutineToday = userService.getUserWithRoutineToday();
		SendMessage.main(whatsAppUserRoutineToday);
	}

}
