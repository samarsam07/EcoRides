package com.samar.ecoRides.dao;

import com.samar.ecoRides.model.Ride;
import com.samar.ecoRides.model.RideParticipant;
import com.samar.ecoRides.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RideParticipantDao extends JpaRepository<RideParticipant,Long> {
    boolean existsByRideAndUser(Ride ride, User user);
    Optional<RideParticipant> findByRideAndUser(Ride ride, User user);
    List<RideParticipant> findByRide(Ride ride);
    List<RideParticipant> findByUser(User user);
}
