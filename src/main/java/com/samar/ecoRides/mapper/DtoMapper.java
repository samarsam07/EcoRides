package com.samar.ecoRides.mapper;

import com.samar.ecoRides.dto.RideDto;
import com.samar.ecoRides.dto.RideParticipantDto;
import com.samar.ecoRides.dto.UserDto;
import com.samar.ecoRides.model.Ride;
import com.samar.ecoRides.model.RideParticipant;
import com.samar.ecoRides.model.User;

import java.util.Collections;
import java.util.stream.Collectors;

public class DtoMapper {
    public UserDto toUserDto(User user) {
        if (user == null) return null;
        UserDto dto = new UserDto();
        dto.setUserId(user.getUserId());
        dto.setUserName(user.getUserName());
        dto.setEmail(user.getEmail());
        return dto;
    }

    public  RideDto toRideDto(Ride ride) {
        if (ride == null) return null;
        RideDto dto = new RideDto();
        dto.setRideId(ride.getRideId());
        dto.setSource(ride.getSource());
        dto.setDestination(ride.getDestination());
        dto.setTime(ride.getTime());
        dto.setCapacity(ride.getCapacity());
        if (ride.getPassengers() != null) {
            dto.setPassengers(
                    ride.getPassengers().stream()
                            .map(user -> toUserDto(user))
                            .collect(Collectors.toList())
            );
            dto.setCurrentUsers(ride.getCurrentUsers());
        } else {
            dto.setPassengers(Collections.emptyList());
            dto.setCurrentUsers(ride.getCurrentUsers());
        }
        dto.setTotalCost(ride.getTotalCost());
        dto.setStatus(ride.getStatus());
        dto.setOrganizerName(
                ride.getOrganizer() != null ? ride.getOrganizer().getUserName() : null
        );
        return dto;
    }

    public  RideParticipantDto toRideParticipantDto(RideParticipant rp) {
        if (rp == null) return null;
        RideParticipantDto dto = new RideParticipantDto();
        dto.setId(rp.getId());
        dto.setStatus(rp.getStatus());
        dto.setJoinedAt(rp.getJoinedAt());
        dto.setUser(toUserDto(rp.getUser()));
        dto.setRide(toRideDto(rp.getRide()));
        return dto;
    }
}
