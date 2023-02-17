package com.micrsoervices.userservice.userservice.serviceImpl;

import com.micrsoervices.userservice.userservice.dto.GetAllHotelByIdDto;
import com.micrsoervices.userservice.userservice.entities.Hotel;
import com.micrsoervices.userservice.userservice.entities.Rating;
import com.micrsoervices.userservice.userservice.exception.ResourceNotFoundException;
import com.micrsoervices.userservice.userservice.entities.User;
import com.micrsoervices.userservice.userservice.externalService.hotel.HotelService;
import com.micrsoervices.userservice.userservice.externalService.rating.service.RatingService;
import com.micrsoervices.userservice.userservice.payload.RateHotelRequestDto;
import com.micrsoervices.userservice.userservice.repositories.UserRepository;
import com.micrsoervices.userservice.userservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RatingService ratingService;

    @Autowired
    private HotelService hotelService;

    @Override
    public User saveUser(User user) {
        String userId = UUID.randomUUID().toString();
        user.setUserId(userId);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getAUser(String userId) {
        return getUserById(userId);
    }

    private User getUserById(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user not found with id " + userId));
        List<Rating> ratingByUserId = ratingService.getRatingByUserId(userId);

        List<String> hotelIds = ratingByUserId.stream().map(e -> e.getHotelId()).collect(Collectors.toList());


        ResponseEntity<List<Hotel>> allHotelById = hotelService.getAllHotelById(new GetAllHotelByIdDto(hotelIds));


        for (Rating rating : ratingByUserId){

            for (Hotel hotel : allHotelById.getBody()){

                if(hotel.getHotelId().equals(rating.getHotelId())){
                    rating.setHotel(hotel);
                }
            }

        }


        user.setRatings(ratingByUserId);
        return user;
    }

    @Override
    public User rateHotel(RateHotelRequestDto request) {

        Hotel hotel = hotelService.getAHotel(request.getHotelId()).getBody();

        if(!Objects.isNull(hotel)){
            Rating rating = new Rating(request);
            Rating ratingResponse = ratingService.create(rating);
            return getAUser(request.getUserId());
        }

       throw new RuntimeException("Invalid data");
    }


}
