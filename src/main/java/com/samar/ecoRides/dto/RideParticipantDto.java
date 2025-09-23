package com.samar.ecoRides.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RideParticipantDto {
    private Long id;
    private UserDto user;
    private RideDto ride;
    private LocalDateTime joinedAt;
    private String status;
}
