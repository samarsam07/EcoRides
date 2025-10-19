package com.samar.ecoRides.dao;

import com.samar.ecoRides.model.Ride;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RideDao extends JpaRepository<Ride,Long> {
    List<Ride> findByStatus(String status);
    List<Ride> findBySourceAndDestinationAndStatus(String source,String destination,String status);
}
