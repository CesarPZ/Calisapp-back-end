package com.calisapp.messageWhatsapp;

import java.util.Set;

import com.calisapp.model.User;
import com.twilio.Twilio; 
import com.twilio.converter.Promoter; 
import com.twilio.rest.api.v2010.account.Message; 
import com.twilio.type.PhoneNumber; 

public class SendMessage { 
    // Find your Account Sid and Token at twilio.com/console 
    public static final String ACCOUNT_SID = "AC0b5fcc93ef4c77b50d48085c3c8a70f0"; 
    public static final String AUTH_TOKEN = "151bc520f7e1317874439f194c23d3bc"; 
 
    public static void main(Set<User> users) { 
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        for(User user: users) {
        	Message message = Message.creator( 
                    new com.twilio.type.PhoneNumber("whatsapp:" + user.getMobileNumber()), 
                    new com.twilio.type.PhoneNumber("whatsapp:+14155238886"),  
                    "Hola "+ user.getName() +" ingresa a: https://calisapp-backend.herokuapp.com/swagger-ui.html para ver tu rutinas")      
                .create(); 
        }
    } 
}