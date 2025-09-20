package com.samar.ecoRides.service;

import com.samar.ecoRides.dao.UserDao;
import com.samar.ecoRides.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserService {

    private static final PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
    @Autowired
    private UserDao userDao;
    public User getUser(String name) {
        User user=userDao.findByUserName(name);
        System.out.println(user);
        return userDao.findByUserName(name);
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
}
