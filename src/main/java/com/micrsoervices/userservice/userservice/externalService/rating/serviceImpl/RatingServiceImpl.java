package com.micrsoervices.userservice.userservice.externalService.rating.serviceImpl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.micrsoervices.userservice.userservice.entities.Rating;
import com.micrsoervices.userservice.userservice.externalService.rating.service.RatingService;
import com.micrsoervices.userservice.userservice.util.ApiConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//@Service
public class RatingServiceImpl  {

//    @Autowired
//    private ApiConnection apiConnection;
//
//    private ObjectMapper objectMapper;
//
//    public RatingServiceImpl() {
//        this.objectMapper = new ObjectMapper();
//    }
//
//    @Override
//    public List<Rating> getRatingByUserId(String userId) {
//
//        String url = "http://localhost:8083/ratings/get-by-userId?userId="+userId;
//
//        String apiResponse = apiConnection.getApi(url);
//
//        Rating[] allRatings = null;
//
//        try {
//            allRatings= objectMapper.readValue(apiResponse,Rating[].class);
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException(e);
//        }
//
//        return List.of(allRatings);
//    }
}
