package com.samar.ecoRides.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PayoutDto {
    private Long rideId;
    private String source;
    private String destination;
    private Integer totalCost;
    private Integer totalCollected;
    private String escrowStatus;
    private String organizerName;
    private String organizerUpiId;
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
	public Integer getTotalCost() {
		return totalCost;
	}
	public void setTotalCost(Integer totalCost) {
		this.totalCost = totalCost;
	}
	public Integer getTotalCollected() {
		return totalCollected;
	}
	public void setTotalCollected(Integer totalCollected) {
		this.totalCollected = totalCollected;
	}
	public String getEscrowStatus() {
		return escrowStatus;
	}
	public void setEscrowStatus(String escrowStatus) {
		this.escrowStatus = escrowStatus;
	}
	public String getOrganizerName() {
		return organizerName;
	}
	public void setOrganizerName(String organizerName) {
		this.organizerName = organizerName;
	}
	public String getOrganizerUpiId() {
		return organizerUpiId;
	}
	public void setOrganizerUpiId(String organizerUpiId) {
		this.organizerUpiId = organizerUpiId;
	}
    
}
