package com.calisapp.services;

import org.springframework.stereotype.Service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;

@Service
public class MessageService {
	public static final String ACCOUNT_SID = "AC5b5d1d02de2f69f1dfca450f1262a8c5";
    public static final String AUTH_TOKEN = "dbbaf8d3bc48003856703342070affdd";
 
    public Message sendMessage(String number, String _message) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                new com.twilio.type.PhoneNumber("whatsapp:+5491165458171"),
                new com.twilio.type.PhoneNumber("whatsapp:+14155238886"),
                _message)
            .create();

		return message;
    }
}
