package com.samar.ecoRides.controller;

import com.samar.ecoRides.dto.RideParticipantDto;
import com.samar.ecoRides.model.RideParticipant;
import com.samar.ecoRides.service.RideParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/join")
public class RideParticipantController {

    @Autowired
    private RideParticipantService rideParticipantService;
    @GetMapping
    public ResponseEntity<List<RideParticipantDto>> getRideParticipantByUser(){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String username=authentication.getName();
        List<RideParticipantDto> rideParticipantDtos=rideParticipantService.getRideParticipantByUser(username);
        return new ResponseEntity<>(rideParticipantDtos, HttpStatus.OK);
    }
    @PostMapping("rideId/{id}")
    public ResponseEntity<RideParticipantDto> joinRide(@PathVariable Long id){
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        String userName=authentication.getName();
        RideParticipantDto rideParticipantDto=rideParticipantService.joinRide(id,userName);
        return new ResponseEntity<>(rideParticipantDto,HttpStatus.CREATED);
    }
}
