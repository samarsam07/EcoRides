package com.samar.ecoRides.service;

import com.samar.ecoRides.dao.UserDao;
import com.samar.ecoRides.dto.UserDto;
import com.samar.ecoRides.model.Ride;
import com.samar.ecoRides.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserService {

    private static final PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
    @Autowired
    private UserDao userDao;
    public UserDto getUser(String name) {
        User user=userDao.findByUserName(name);

        if(user!=null){
            UserDto userDto=new UserDto();
            userDto.setUserId(user.getUserId());
            userDto.setEmail(user.getEmail());
            userDto.setUserName(user.getUserName());
            userDto.setJoinedRides(user.getJoinedRides());
            userDto.setOrganizedRides(user.getOrganizedRides());
            return userDto;
        }else{
            return null;
        }

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
}
