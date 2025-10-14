package com.samar.ecoRides.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PayoutDto {
    private Long rideId;
    private String source;
    private String destination;
    private Integer totalCost;
    private Integer totalCollected;
    private String escrowStatus;
    private String organizerName;
    private String organizerUpiId;
}
