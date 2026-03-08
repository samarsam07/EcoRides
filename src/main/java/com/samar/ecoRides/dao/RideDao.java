package com.samar.ecoRides.dao;

import com.samar.ecoRides.model.Ride;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface RideDao extends JpaRepository<Ride,Long> {
    List<Ride> findByStatus(String status);
    List<Ride> findBySourceAndDestinationAndStatus(String source,String destination,String status);

    @Query("SELECT r FROM Ride r WHERE " +
            "(:source IS NULL OR LOWER(r.source) LIKE LOWER(CONCAT('%', :source, '%'))) AND " +
            "(:destination IS NULL OR LOWER(r.destination) LIKE LOWER(CONCAT('%', :destination, '%'))) AND " +
            "r.status = :status AND " +
            "(:startDate IS NULL OR r.time >= :startDate) AND " +
            "(:endDate IS NULL OR r.time <= :endDate) AND " +
            "(:maxPrice IS NULL OR r.costPerPassenger <= :maxPrice)")
    List<Ride> findAvailableRidesWithFilters(
            @Param("source") String source,
            @Param("destination") String destination,
            @Param("status") String status,
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate,
            @Param("maxPrice") Integer maxPrice
    );
}
