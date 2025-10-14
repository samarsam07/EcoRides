package com.samar.ecoRides.mapper;

import com.samar.ecoRides.dto.*;
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
        dto.setPhoneNumber(user.getPhoneNumber());
        return dto;
    }

    public  RideDto toRideDto(Ride ride) {
        if (ride == null) return null;
        RideDto dto = new RideDto();
        dto.setRideId(ride.getRideId());
        dto.setSource(ride.getSource());
        dto.setCostPerPassenger(ride.getCostPerPassenger());
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

    public PaymentDto toPaymentDto(RideParticipant participant) {
        if (participant == null) return null;

        PaymentDto dto = new PaymentDto();
        dto.setParticipantId(participant.getId());
        dto.setUserName(participant.getUser().getUserName());
        dto.setEmail(participant.getUser().getEmail());
        dto.setShareAmount(participant.getShareAmount());
        dto.setPaymentId(participant.getPaymentId());
        dto.setPaymentStatus(participant.getPaymentStatus());
        dto.setJoinedAt(participant.getJoinedAt());

        return dto;
    }

    public  PayoutDto toPayoutDto(Ride ride) {
        if (ride == null) return null;

        PayoutDto dto = new PayoutDto();
        dto.setRideId(ride.getRideId());
        dto.setSource(ride.getSource());
        dto.setDestination(ride.getDestination());
        dto.setTotalCost(ride.getTotalCost());
        dto.setTotalCollected(ride.getTotalCollected());
        dto.setEscrowStatus(ride.getEscrowStatus());
        dto.setOrganizerName(ride.getOrganizer().getUserName());
        dto.setOrganizerUpiId(ride.getOrganizerUpiId());

        return dto;
    }
}
