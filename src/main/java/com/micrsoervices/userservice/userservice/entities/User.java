package com.micrsoervices.userservice.userservice.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    @Id
    private String userId;

    private String name;

    private String email;

    private String about;

    @Transient
    private List<Rating> ratings;

}
