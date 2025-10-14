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

    public boolean isFullyPaid() {
        return Objects.equals(totalCollected, totalCost);
    }
}
