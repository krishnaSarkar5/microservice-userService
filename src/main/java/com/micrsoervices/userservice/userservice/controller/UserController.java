package com.micrsoervices.userservice.userservice.controller;

import com.micrsoervices.userservice.userservice.entities.User;
import com.micrsoervices.userservice.userservice.payload.RateHotelRequestDto;
import com.micrsoervices.userservice.userservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user){
        User savedUser = userService.saveUser(user);
        return new ResponseEntity<User>(savedUser, HttpStatus.CREATED);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getAUser(@PathVariable String userId){
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
}
