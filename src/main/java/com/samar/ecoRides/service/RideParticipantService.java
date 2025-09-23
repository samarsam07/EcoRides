package com.samar.ecoRides.service;


import com.samar.ecoRides.dao.RideParticipantDao;
import com.samar.ecoRides.dto.RideParticipantDto;
import com.samar.ecoRides.mapper.DtoMapper;
import com.samar.ecoRides.model.Ride;
import com.samar.ecoRides.model.RideParticipant;
import com.samar.ecoRides.model.User;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class RideParticipantService {

    @Autowired
    private UserService userService;
    @Autowired
    private RideService rideService;
    @Autowired
    private RideParticipantDao rideParticipantDao;
    private final DtoMapper dtoMapper=new DtoMapper();

    public List<RideParticipantDto> getRideParticipantByUser(String username) {
        User user=userService.findByUserName(username);
        List<RideParticipant> rideParticipants= rideParticipantDao.findByUser(user);
        List<RideParticipantDto> rideParticipantDtos=new ArrayList<>();
        for(RideParticipant participant:rideParticipants){
            rideParticipantDtos.add(dtoMapper.toRideParticipantDto(participant));
        }
        return rideParticipantDtos;
    }

    public RideParticipantDto joinRide(Long rideId,String username){
        Ride ride = rideService.findById(rideId);
        User user = userService.findByUserName(username);
        if(ride==null || user==null){
            return null;
        }
        if (rideParticipantDao.existsByRideAndUser(ride, user)) {
            throw new IllegalStateException("User already joined this ride");
        }
        if (ride.getCurrentUsers()>=ride.getCapacity()){
            throw new IllegalStateException("No seat Available");
        }
        RideParticipant rideParticipant=new RideParticipant();
        rideParticipant.setRide(ride);
        rideParticipant.setUser(user);
        rideParticipant.setStatus("REQUESTED");
        rideParticipantDao.save(rideParticipant);
        ride.setCurrentUsers(ride.getCurrentUsers()+1);
        rideService.saveRide(ride);
        if(Objects.equals(ride.getCapacity(), ride.getCurrentUsers())){
            ride.setStatus("CONFIRM");
            rideService.saveRide(ride);
        }
        return dtoMapper.toRideParticipantDto(rideParticipant);
    }
}
