package com.micrsoervices.userservice.userservice.payload;

import lombok.Data;

@Data
public class RateHotelRequestDto {

    private String userId;

    private String hotelId;

    private int rating;

    private String feedback;
}
