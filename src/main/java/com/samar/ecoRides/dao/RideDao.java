package com.samar.ecoRides.dao;

import com.samar.ecoRides.model.Ride;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RideDao extends JpaRepository<Ride,Long> {
}
