package com.calisapp.messageWhatsapp;

import java.util.Set;

import com.calisapp.model.User;
import com.twilio.Twilio; 
import com.twilio.converter.Promoter; 
import com.twilio.rest.api.v2010.account.Message; 
import com.twilio.type.PhoneNumber; 

public class SendMessage { 
    // Find your Account Sid and Token at twilio.com/console 
    public static final String ACCOUNT_SID = "AC6c3a49d57c646bb77883e8713117afe3"; 
    public static final String AUTH_TOKEN = "37a5fe05f2ce4463ba0850483fab4fce"; 
 
    public static void main(Set<User> users) { 
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        for(User user: users) {
        	Message message = Message.creator( 
                    new com.twilio.type.PhoneNumber("whatsapp:" + "+549" +user.getMobileNumber()), 
                    new com.twilio.type.PhoneNumber("whatsapp:+14155238886"),  
                    "Hola "+ user.getName() +"! El dia de hoy tenes rutinas pendientes, para verlas ingresa a: https://calisapp2.000webhostapp.com/#/myRoutineToday")      
                .create(); 
        }
    } 
}