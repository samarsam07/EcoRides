package com.samar.ecoRides.controller;

import com.samar.ecoRides.dto.UserDto;
import com.samar.ecoRides.model.User;
import com.samar.ecoRides.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
     private UserService userService;

    @GetMapping
    public ResponseEntity<UserDto> getUser(){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String name=authentication.getName();
        UserDto userDto=userService.getUser(name);
        if(userDto==null)return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(userDto,HttpStatus.FOUND);
    }

    @PutMapping
    public ResponseEntity<User> updateUser(@RequestBody User user){
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        String name=authentication.getName();
        User res=userService.updateUser(name,user);
        if(res==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(res,HttpStatus.OK);
    }
    @DeleteMapping
    private ResponseEntity<User> deleteUser(){
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        String name=authentication.getName();
        User user=userService.deleteUser(name);
        if(user==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

}
