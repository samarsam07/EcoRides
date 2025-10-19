package com.samar.ecoRides.controller;

import com.samar.ecoRides.dto.RideDto;
import com.samar.ecoRides.model.Ride;
import com.samar.ecoRides.service.RideService;
import com.samar.ecoRides.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rides")
public class RideController {
    @Autowired
    private RideService rideService;

    @Autowired
    private UserService userService;

    @GetMapping("/id/{rideId}")
    public ResponseEntity<RideDto> getRideById(@PathVariable Long rideId){
        RideDto rideDto=rideService.getRideById(rideId);
        if(rideDto==null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return  new ResponseEntity<>(rideDto,HttpStatus.OK);
    }
    @PutMapping("id/{rideId}")
    public ResponseEntity<RideDto> updateRideById(@PathVariable Long rideId,@RequestBody Ride ride){
        RideDto rideDto=rideService.updateRideById(rideId,ride);
        if(rideDto==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(rideDto,HttpStatus.ACCEPTED);
    }
    @GetMapping("/all")
    public ResponseEntity<List<RideDto>> getAllOrganizedRidesOfUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();

        List<Ride> rides = userService.findOrganizedRidesByUsername(userName);

        if (rides.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        List<RideDto> rideDtos = rideService.convertRidesToDtos(rides); // We'll add this helper method next
        return new ResponseEntity<>(rideDtos, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createRide(@RequestBody Ride ride){
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String userName = authentication.getName();
            rideService.createRide(userName, ride);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/available")
    public ResponseEntity<List<RideDto>> getAllAvailableRides() {
        List<RideDto> rides = rideService.getAllAvailableRides();
        if (rides.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(rides, HttpStatus.OK);
    }
    @DeleteMapping("/id/{rideId}")
    public ResponseEntity<HttpStatus> deleteRideById(@PathVariable Long rideId){
        try{
            Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
            String username=authentication.getName();
            rideService.deleteRideById(username,rideId);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/available/filter/{source}/{destination}")
    public ResponseEntity<List<RideDto>> filterRideBySourceAndDestination(
            @PathVariable String source,@PathVariable String destination
    ){
        List<RideDto> rides=rideService.filterRideBySourceAndDestination(source,destination);
        if(rides.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(rides,HttpStatus.OK);
    }

}
