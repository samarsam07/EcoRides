package com.samar.ecoRides.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDto {
    private Long participantId;
    private String userName;
    private String email;
    private Integer shareAmount;
    private String paymentId;
    private String paymentStatus;
    private LocalDateTime joinedAt;
}
