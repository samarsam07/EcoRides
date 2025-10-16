package com.samar.ecoRides.controller;

import com.samar.ecoRides.model.User;
import com.samar.ecoRides.service.UserDetailsServiceImpl;
import com.samar.ecoRides.service.UserService;
import com.samar.ecoRides.utilies.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
@Slf4j
public class PublicController {
    private static final Logger log = LoggerFactory.getLogger(PublicController.class);
    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping("/test")
    public ResponseEntity<String> check(){
       return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping("/signup")
    public void signup(@RequestBody User user){
        userService.createUser(user);
    }
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user){
        try {
            Authentication authentication=authenticationManager
                    .authenticate(
                            new UsernamePasswordAuthenticationToken(user.getUserName(),user.getPassword())
                    );
            UserDetails userDetails=userDetailsService.loadUserByUsername(user.getUserName());
            String jwt=jwtUtil.generateToken(userDetails.getUsername());
            return ResponseEntity.ok(jwt);
        }catch (Exception e){

            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
