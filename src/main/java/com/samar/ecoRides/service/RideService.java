package com.samar.ecoRides.service;

import com.samar.ecoRides.dao.RideDao;
import com.samar.ecoRides.dao.UserDao;
import com.samar.ecoRides.dto.RideDto;
import com.samar.ecoRides.model.Ride;
import com.samar.ecoRides.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RideService {
    @Autowired
    private RideDao rideDao;
    @Autowired
    private UserService userService;


    public RideDto getRideById(Long myId) {
        Ride ride= rideDao.findById(myId).get();
        RideDto rideDto=new RideDto();
        if(ride!=null){
            rideDto.setCapacity(ride.getCapacity());
            rideDto.setDestination(ride.getDestination());
            rideDto.setCurrentUsers(ride.getCurrentUsers());
            rideDto.setOrganizerName(ride.getOrganizer().getUserName());
            rideDto.setSource(ride.getSource());
            rideDto.setStatus(ride.getStatus());
            rideDto.setRideId(ride.getRideId());
            rideDto.setTime(ride.getTime());
            rideDto.setTotalCost(ride.getTotalCost());
        }
        return rideDto;
    }

    public List<RideDto> getAllOrganizedRidesOfUser(String userName) {
        User user= userService.findByUserName(userName);
        List<Ride>rides=user.getOrganizedRides();
        List<RideDto> rideDtos=new ArrayList<>();
        for(Ride ride:rides){
            RideDto rideDto=new RideDto();
            rideDto.setCapacity(ride.getCapacity());
            rideDto.setDestination(ride.getDestination());
            rideDto.setCurrentUsers(ride.getCurrentUsers());
            rideDto.setOrganizerName(ride.getOrganizer().getUserName());
            rideDto.setSource(ride.getSource());
            rideDto.setStatus(ride.getStatus());
            rideDto.setRideId(ride.getRideId());
            rideDto.setTime(ride.getTime());
            rideDto.setTotalCost(ride.getTotalCost());
            rideDtos.add(rideDto);
        }
        return rideDtos;
    }

    public void createRide(String userName, Ride ride) {
        User user=userService.findByUserName(userName);
        ride.setTime(LocalDateTime.now());
        ride.setStatus("REQUESTED");
        ride.setOrganizer(user);
        rideDao.save(ride);
        user.getOrganizedRides().add(ride);
    }
}
