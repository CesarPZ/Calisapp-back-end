package com.calisapp.messageWhatsapp;

import java.util.List;

import com.twilio.Twilio; 
import com.twilio.rest.api.v2010.account.Message; 
 
public class SendMessage { 
    // Find your Account Sid and Token at twilio.com/console 
    public static final String ACCOUNT_SID = "ACb28e9ac2f4f2469959a35f64ee8d368a"; 
    public static final String AUTH_TOKEN = "fdd7be705b7249925deae4937e1121e1"; 
 
    public static void main(List<String> numerosWhatsapp) { 
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN); 
        for(String numeroWhatsapp: numerosWhatsapp) {
        	Message message = Message.creator( 
                    new com.twilio.type.PhoneNumber("whatsapp:"+ numeroWhatsapp ), 
                    new com.twilio.type.PhoneNumber("whatsapp:+14155238886"),  
                    "Hoy tienes rutinas para realizar, para velas ingresa en http://localhost:4200/#/myRoutineToday")      
                .create(); 
        	} 
        } 
}