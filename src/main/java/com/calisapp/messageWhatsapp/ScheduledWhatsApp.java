package com.calisapp.messageWhatsapp;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.calisapp.services.UserService;

@Configuration
@EnableScheduling
public class ScheduledWhatsApp {
	private UserService userService;
	
	@Scheduled(cron = "0 0 12 * * ?", zone = "America/Buenos_Aires")
	public void scheduleTaskUsingCronExpression() {
		List<String> whatsAppUserRoutineToday = userService.getUserWithRoutineToday();
		SendMessage.main(whatsAppUserRoutineToday);
	}

}
