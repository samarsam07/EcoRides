package com.samar.ecoRides.scheduler;

import com.samar.ecoRides.dao.RideDao;
import com.samar.ecoRides.model.Ride;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;
import java.util.List;

public class RideStatusScheduler {

    @Autowired
    RideDao rideDao;

    @Scheduled(fixedRate = 60000)
    public  void checkRideStatus() {
        System.out.println("Running");
        List<Ride> requestedRides = rideDao.findByStatus("REQUESTED");
        for (Ride ride : requestedRides) {
            if (ride.getTime().plusMinutes(15).isBefore(LocalDateTime.now())) {
                if (ride.getPassengers().isEmpty()) {
                    ride.setStatus("EXPIRED");
                    System.out.println("Ride ID " + ride.getRideId() + " has expired.");
                } else {
                    ride.setStatus("REQUESTED");
                    System.out.println("Ride ID " + ride.getRideId() + " is now pending organizer confirmation.");
                }
                rideDao.save(ride);
            }
        }
    }

}
