package com.samar.ecoRides.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long userId;
    private  String userName;
    private String email;
}
