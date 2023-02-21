package com.micrsoervices.userservice.userservice.controller;

import com.micrsoervices.userservice.userservice.entities.User;
import com.micrsoervices.userservice.userservice.payload.RateHotelRequestDto;
import com.micrsoervices.userservice.userservice.services.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.ILoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user){
        User savedUser = userService.saveUser(user);
        return new ResponseEntity<User>(savedUser, HttpStatus.CREATED);
    }

    int retryCount=0;

    //here to fetch a particular user details we depend upon two external service..... we need a circuit breaker pattern here


//    retry basically call defined no of times before conclude it as a failed call

    @GetMapping("/{userId}")
//    @CircuitBreaker(name = "ratingHotelBreaker",fallbackMethod = "ratingHotelFallback")
//    @Retry(name = "ratingHotelService" )
    @RateLimiter(name = "userRateLimiter" ,fallbackMethod = "ratingHotelFallback")
    public ResponseEntity<User> getAUser(@PathVariable String userId){
        retryCount++;
        System.out.println("retrying.....  "+retryCount);
        User user = userService.getAUser(userId);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<User>> getAllUser(){
        List<User> allUser = userService.getAllUsers();
        return new ResponseEntity<List<User>>(allUser, HttpStatus.OK);
    }

    @PostMapping("/rate-hotel")
    public ResponseEntity<User> rateHotel(@RequestBody RateHotelRequestDto request){
        User savedUser = userService.rateHotel(request);
        return new ResponseEntity<User>(savedUser, HttpStatus.CREATED);
    }










    // creatting fallback methods

    public ResponseEntity<User> ratingHotelFallback(String  userId,Exception ex){

        ex.printStackTrace();
        User dummYUser = User.builder().name("Dummy").email("dummy@dummy.com").about("This user is created dummy").build();

        log.info("Fallback is executed because service is down : "+ex.getMessage());

        return new ResponseEntity<User>(dummYUser,HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
