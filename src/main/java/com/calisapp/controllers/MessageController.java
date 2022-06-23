package com.calisapp.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.calisapp.services.MessageService;
import com.twilio.rest.api.v2010.account.Message;

@RestController
@EnableAutoConfiguration
@CrossOrigin(origins = "http://localhost:4200",maxAge = 3600)
public class MessageController {
	@Autowired
	private MessageService messageService;
	
	@PostMapping("/api/createMessagge")
	public ResponseEntity<?> createMessage(@Valid @RequestParam ("number") String number,
									  @RequestParam ("message") String message) {	
		
		Message sendmessage = messageService.sendMessage(number, message);

		return ResponseEntity.ok().body(sendmessage);	
	}
}
