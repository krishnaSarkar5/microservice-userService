package com.micrsoervices.userservice.userservice.services;

import com.micrsoervices.userservice.userservice.entities.User;
import com.micrsoervices.userservice.userservice.payload.RateHotelRequestDto;

import java.util.List;

public interface UserService {

    User saveUser(User user);

    List<User> getAllUsers();

    User getAUser(String userId);

    User rateHotel(RateHotelRequestDto request);
}
