package com.samar.ecoRides.dto;


import com.samar.ecoRides.model.Ride;

import java.util.ArrayList;
import java.util.List;

public class UserDto {

    private Long userId;
    private  String userName;
    private String email;
    private List<Ride> organizedRides=new ArrayList<>();
    private  List<Ride> joinedRides=new ArrayList<>();

    public UserDto(String userName, String email, List<Ride> joinedRides, List<Ride> organizedRides, Long userId) {
        this.userName = userName;
        this.email = email;
        this.joinedRides = joinedRides;
        this.organizedRides = organizedRides;
        this.userId = userId;
    }

    public List<Ride> getOrganizedRides() {
        return organizedRides;
    }

    public void setOrganizedRides(List<Ride> organizedRides) {
        this.organizedRides = organizedRides;
    }

    public List<Ride> getJoinedRides() {
        return joinedRides;
    }

    public void setJoinedRides(List<Ride> joinedRides) {
        this.joinedRides = joinedRides;
    }

    public UserDto() {
    }



    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}
