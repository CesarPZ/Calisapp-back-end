package com.calisapp.messageWhatsapp;

import java.util.Calendar;
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
	
	@Scheduled(cron = "0 * * * * ?", zone = "America/Buenos_Aires")
	public void scheduleTaskUsingCronExpression() {
		System.out.println("Current time is :: " + Calendar.getInstance().getTime());
		Set<User> whatsAppUserRoutineToday = userService.getUserWithRoutineToday();
		SendMessage.main(whatsAppUserRoutineToday);
	}

}
