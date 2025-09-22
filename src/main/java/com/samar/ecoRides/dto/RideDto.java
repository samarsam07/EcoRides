package com.samar.ecoRides.dto;

import java.time.LocalDateTime;

public class RideDto {
    private Long rideId;
    private String source;
    private String destination;
    private LocalDateTime time;
    private Integer capacity;
    private Integer currentUsers;
    private Integer totalCost;
    private String status;
    private String organizerName;

    public RideDto(){

    }

    public RideDto(Long rideId, String source, String destination, LocalDateTime time, Integer capacity, Integer currentUsers, Integer totalCost, String status, String organizerName) {
        this.rideId = rideId;
        this.source = source;
        this.destination = destination;
        this.time = time;
        this.capacity = capacity;
        this.currentUsers = currentUsers;
        this.totalCost = totalCost;
        this.status = status;
        this.organizerName = organizerName;
    }

    public Long getRideId() {
        return rideId;
    }

    public void setRideId(Long rideId) {
        this.rideId = rideId;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Integer getCurrentUsers() {
        return currentUsers;
    }

    public void setCurrentUsers(Integer currentUsers) {
        this.currentUsers = currentUsers;
    }

    public Integer getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Integer totalCost) {
        this.totalCost = totalCost;
    }

    public String getOrganizerName() {
        return organizerName;
    }

    public void setOrganizerName(String organizerName) {
        this.organizerName = organizerName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
