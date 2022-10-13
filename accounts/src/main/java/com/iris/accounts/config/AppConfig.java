package com.iris.accounts.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Configuration
public class AppConfig {

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @Bean
    public HttpHeaders headerConfig() {

        HttpHeaders httpHeader = new HttpHeaders();
        httpHeader.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        httpHeader.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        httpHeader.setContentType(MediaType.APPLICATION_JSON);
        return httpHeader;
    }

}
