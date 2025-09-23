package com.samar.ecoRides.dto;

import com.samar.ecoRides.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RideDto {
    private Long rideId;
    private String source;
    private String destination;
    private LocalDateTime time;
    private Integer capacity;
    private Integer currentUsers;
    private Integer totalCost;
    private String status;
    private String organizerName;
    private List<UserDto> passengers;
}
