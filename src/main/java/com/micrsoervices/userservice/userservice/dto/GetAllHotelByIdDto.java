package com.micrsoervices.userservice.userservice.dto;

import lombok.Data;

import java.util.List;

@Data
public class GetAllHotelByIdDto {
    List<String> idList;


    public GetAllHotelByIdDto(List<String> idList){
        this.idList=idList;
    }
}
