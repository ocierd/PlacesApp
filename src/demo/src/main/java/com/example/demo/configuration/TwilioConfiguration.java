package com.example.demo.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import com.twilio.Twilio;

import jakarta.annotation.PostConstruct;

@Configuration
public class TwilioConfiguration {

    @Value("${twilio.config.account_sid}")
    private String TWILIO_ACCOUNT_SID;

    @Value("${twilio.config.auth_token}")
    private String TWILIO_AUTH_TOKEN;

    @PostConstruct
    private void initTwilio() {
        Twilio.init(TWILIO_ACCOUNT_SID, TWILIO_AUTH_TOKEN);
    }
}


// var twiliConfig= new TwilioConfiguration();
//  twiliConfig.initTwilio();