package com.micrsoervices.userservice.userservice.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.micrsoervices.userservice.userservice.payload.RateHotelRequestDto;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Rating {

    private String ratingId;

    private String userId;

    private String hotelId;

    private int rating;

    private String feedback;

//    @JsonIgnore
    private Hotel hotel;


    public Rating(RateHotelRequestDto request){
        this.userId=request.getUserId();
        this.hotelId = request.getHotelId();
        this.rating = request.getRating();
        this.feedback = request.getFeedback();
    }
}
