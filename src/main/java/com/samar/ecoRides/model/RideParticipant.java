package com.samar.ecoRides.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ride_participants")
public class RideParticipant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ride_id", nullable = false)
    private Ride ride;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime joinedAt;


    @Column(nullable = false)
    private String status;

    @Column
    private String paymentId;

    @Column
    private String paymentStatus = "PENDING";

    private String orderId;

    @Column
    private Integer shareAmount;

	public RideParticipant(Long id, Ride ride, User user, LocalDateTime joinedAt, String status, String paymentId,
			String paymentStatus, String orderId, Integer shareAmount) {
		super();
		this.id = id;
		this.ride = ride;
		this.user = user;
		this.joinedAt = joinedAt;
		this.status = status;
		this.paymentId = paymentId;
		this.paymentStatus = paymentStatus;
		this.orderId = orderId;
		this.shareAmount = shareAmount;
	}

	public RideParticipant() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Ride getRide() {
		return ride;
	}

	public void setRide(Ride ride) {
		this.ride = ride;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public LocalDateTime getJoinedAt() {
		return joinedAt;
	}

	public void setJoinedAt(LocalDateTime joinedAt) {
		this.joinedAt = joinedAt;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public Integer getShareAmount() {
		return shareAmount;
	}

	public void setShareAmount(Integer shareAmount) {
		this.shareAmount = shareAmount;
	}
    
}
