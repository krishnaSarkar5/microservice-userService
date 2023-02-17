package com.micrsoervices.userservice.userservice.externalService.rating.service;

import com.micrsoervices.userservice.userservice.entities.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
@FeignClient(name = "RATING-SERVICE")
public interface RatingService {

    @GetMapping("/ratings/get-by-userId")
    List<Rating> getRatingByUserId(@RequestParam("userId") String userId);

    @PostMapping("/ratings/create-rating")
    public Rating create(@RequestBody Rating rating);
}
