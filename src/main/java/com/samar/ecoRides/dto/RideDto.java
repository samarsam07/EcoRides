package com.samar.ecoRides.dto;

import com.samar.ecoRides.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RideDto {
    private Long rideId;
    private String source;
    private String destination;
    private LocalDateTime time;
    private Integer capacity;
    private Integer currentUsers;
    private Integer totalCost;
    private Integer costPerPassenger;
    private String status;
    private String organizerName;
    private List<UserDto> passengers;
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
	public Integer getCostPerPassenger() {
		return costPerPassenger;
	}
	public void setCostPerPassenger(Integer costPerPassenger) {
		this.costPerPassenger = costPerPassenger;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getOrganizerName() {
		return organizerName;
	}
	public void setOrganizerName(String organizerName) {
		this.organizerName = organizerName;
	}
	public List<UserDto> getPassengers() {
		return passengers;
	}
	public void setPassengers(List<UserDto> passengers) {
		this.passengers = passengers;
	}
    
}
