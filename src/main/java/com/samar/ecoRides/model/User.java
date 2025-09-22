package com.samar.ecoRides.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(
        name = "users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"email", "userName"})
        }
)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    @Column(nullable = false)
    private  String userName;
    @Column(nullable = false)
    private String email;
    private String password;
    private String walletAddress;
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "organizer", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Ride> organizedRides = new ArrayList<>();
    @ManyToMany(mappedBy = "passengers")
    private List<Ride> joinedRides = new ArrayList<>();


    public User() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getWalletAddress() {
        return walletAddress;
    }

    public void setWalletAddress(String walletAddress) {
        this.walletAddress = walletAddress;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
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
}
