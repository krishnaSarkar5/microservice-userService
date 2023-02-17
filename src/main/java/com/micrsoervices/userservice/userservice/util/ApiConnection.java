package com.micrsoervices.userservice.userservice.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.Map;

@Component
public class ApiConnection {

    private RestTemplate restTemplate;

    public ApiConnection() {
        this.restTemplate = new RestTemplate();
    }


    public String getApi(String url){

        HttpHeaders headers = new HttpHeaders();

// set `Content-Type` and `Accept` headers
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

// example of custom header
        headers.set("X-Request-Source", "Desktop");

// build the request
        HttpEntity request = new HttpEntity(headers);

// make an HTTP GET request with headers
        ResponseEntity<String> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                request,
                String.class,
                1
        );
        return response.getBody();
    }



    public String postApi(String url,Map<String,Object> requestBodyMap){

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        ObjectMapper objectMapper = new ObjectMapper();

        String requestBodyString = "";
        try {
            requestBodyString =  objectMapper.writeValueAsString(requestBodyMap);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        HttpEntity<String> entity = new HttpEntity<>(requestBodyString,headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

        return response.getBody();
    }
}
