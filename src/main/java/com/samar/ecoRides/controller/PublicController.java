package com.samar.ecoRides.controller;

import com.samar.ecoRides.model.User;
import com.samar.ecoRides.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {
    @Autowired
    private UserService userService;
    @GetMapping("/test")
    public ResponseEntity<String> check(){
       return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping
    public void createUser(@RequestBody User user){
        userService.createUser(user);
    }
}
