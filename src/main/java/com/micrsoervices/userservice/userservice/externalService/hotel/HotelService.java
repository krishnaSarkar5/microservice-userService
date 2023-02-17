package com.micrsoervices.userservice.userservice.externalService.hotel;

import com.micrsoervices.userservice.userservice.dto.GetAllHotelByIdDto;
import com.micrsoervices.userservice.userservice.entities.Hotel;
import com.micrsoervices.userservice.userservice.entities.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "HOTEL-SERVICE")
public interface HotelService {

    @PostMapping("/hotels/get-all-by-id")
    ResponseEntity<List<Hotel>> getAllHotelById(@RequestBody GetAllHotelByIdDto request);

    @GetMapping("/hotels/{hotelId}")
    public ResponseEntity<Hotel> getAHotel(@PathVariable String hotelId);



}
