package com.samar.ecoRides.service;

import com.samar.ecoRides.dao.UserDao;
import com.samar.ecoRides.dto.UserDto;
import com.samar.ecoRides.mapper.DtoMapper;
import com.samar.ecoRides.model.Ride;
import com.samar.ecoRides.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private static final PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
    private static final DtoMapper dtoMapper=new DtoMapper();
    @Autowired
    private UserDao userDao;


    public UserDto getUser(String name) {
        User user=userDao.findByUserName(name);
        if(user!=null){
            return dtoMapper.toUserDto(user);
        }
            return null;
    }

    public void createUser(User user) {
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setCreatedAt(LocalDateTime.now());
            userDao.save(user);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public User updateUser(String name, User user) {
        User old=userDao.findByUserName(name);
        if(user!=null){
            old.setUserName(user.getUserName());
            old.setEmail(user.getEmail());
            old.setPassword(passwordEncoder.encode(user.getPassword()));
            old.setCreatedAt(LocalDateTime.now());
        }else{
            return null;
        }
        userDao.save(old);
        return old;
    }

    public User deleteUser(String name) {
        User user=userDao.findByUserName(name);
        userDao.delete(user);
        return user;
    }
    public User findByUserName(String username){
        return userDao.findByUserName(username);
    }

    public void saveUser(User user){
        userDao.save(user);
    }

    @Transactional(readOnly = true) // readOnly is a performance optimization for GET operations
    public List<Ride> findOrganizedRidesByUsername(String username) {
        User user = userDao.findByUserName(username);
        // This forces the lazy collection to be loaded within the active transaction
        List<Ride> rides = new ArrayList<>(user.getOrganizedRides());
        return rides;
    }
}
