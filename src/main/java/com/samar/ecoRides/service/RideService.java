package com.samar.ecoRides.service;

import com.samar.ecoRides.dao.RideDao;
import com.samar.ecoRides.dao.UserDao;
import com.samar.ecoRides.dto.RideDto;
import com.samar.ecoRides.mapper.DtoMapper;
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

    private final DtoMapper dtoMapper=new DtoMapper();

    public RideDto getRideById(Long myId) {
        Ride ride= rideDao.findById(myId).get();
        return dtoMapper.toRideDto(ride);
    }

    public List<RideDto> getAllOrganizedRidesOfUser(String userName) {
        User user= userService.findByUserName(userName);
        List<Ride>rides=user.getOrganizedRides();
        List<RideDto> rideDtos=new ArrayList<>();
        for(Ride ride:rides){
            rideDtos.add(dtoMapper.toRideDto(ride));
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

    public void saveRide(Ride ride){
        rideDao.save(ride);
    }

    public Ride findById(Long rideId){
        return rideDao.findById(rideId).get();
    }

    public RideDto updateRideById(Long rideId,Ride ride) {
        Ride old=rideDao.findById(rideId).get();
        if(old!=null){
            old.setSource(ride.getSource());
            old.setDestination(ride.getDestination());
            old.setTime(LocalDateTime.now());
            old.setCapacity(ride.getCapacity());
            old.setTotalCost(ride.getTotalCost());
            int size=old.getPassengers().size();
            old.setCurrentUsers(ride.getCurrentUsers()+size);
            if(old.getCurrentUsers()==old.getCapacity())
                old.setStatus("CONFIRM");
            rideDao.save(old);
            return dtoMapper.toRideDto(old);
        }
        return null;
    }

    public void deleteRideById(String username, Long rideId) {
        try {
            User user=userService.findByUserName(username);
            Optional<Ride> deleted=rideDao.findById(rideId);
            boolean removed=user.getOrganizedRides().removeIf(x->x.getRideId().equals(rideId));
            if(removed){
                userService.saveUser(user);
                rideDao.deleteById(rideId);
            }
        } catch (Exception e) {
//            log.error("kuch fat gaya h {}",e.getMessage());
            System.out.println(e.getMessage());
        }
    }
}
