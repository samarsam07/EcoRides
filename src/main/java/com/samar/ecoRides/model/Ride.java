package com.samar.ecoRides.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "rides")
public class Ride {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rideId;
    @Column(nullable = false)
    private String source;
    @Column(nullable = false)
    private String destination;
    private LocalDateTime time;
    @Column(nullable = false)
    private Integer capacity;
    private Integer currentUsers;
    @Column(nullable = false)
    private Integer totalCost;
    private Integer costPerPassenger;
    private String status;
    @ManyToOne
    @JsonBackReference
    private User organizer;

    @Column(nullable = false)
    private String escrowStatus = "PENDING";

    @Column
    private Integer totalCollected = 0;

    @Column
    private String organizerUpiId;


    @ManyToMany
    @JoinTable(
            name = "ride_participants",
            joinColumns = @JoinColumn(name = "ride_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> passengers = new ArrayList<>();
    

    public Ride(Long rideId, String source, String destination, LocalDateTime time, Integer capacity,
			Integer currentUsers, Integer totalCost, Integer costPerPassenger, String status, User organizer,
			String escrowStatus, Integer totalCollected, String organizerUpiId, List<User> passengers) {
		super();
		this.rideId = rideId;
		this.source = source;
		this.destination = destination;
		this.time = time;
		this.capacity = capacity;
		this.currentUsers = currentUsers;
		this.totalCost = totalCost;
		this.costPerPassenger = costPerPassenger;
		this.status = status;
		this.organizer = organizer;
		this.escrowStatus = escrowStatus;
		this.totalCollected = totalCollected;
		this.organizerUpiId = organizerUpiId;
		this.passengers = passengers;
	}

    
	public Ride() {
		super();
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


	public User getOrganizer() {
		return organizer;
	}


	public void setOrganizer(User organizer) {
		this.organizer = organizer;
	}


	public String getEscrowStatus() {
		return escrowStatus;
	}


	public void setEscrowStatus(String escrowStatus) {
		this.escrowStatus = escrowStatus;
	}


	public Integer getTotalCollected() {
		return totalCollected;
	}


	public void setTotalCollected(Integer totalCollected) {
		this.totalCollected = totalCollected;
	}


	public String getOrganizerUpiId() {
		return organizerUpiId;
	}


	public void setOrganizerUpiId(String organizerUpiId) {
		this.organizerUpiId = organizerUpiId;
	}


	public List<User> getPassengers() {
		return passengers;
	}


	public void setPassengers(List<User> passengers) {
		this.passengers = passengers;
	}


	public boolean isFullyPaid() {
        return Objects.equals(totalCollected, totalCost);
    }
}
