package com.example.taskmanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.taskmanager.entity.User;
import com.example.taskmanager.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    // REGISTER
    public boolean register(User user) {

        // check if email already exists
        if (userRepo.findByEmail(user.getEmail()).isPresent()) {
            return false;
        }

        // encrypt password
        user.setPassword(encoder.encode(user.getPassword()));
        userRepo.save(user);

        return true;
    }

    // LOGIN
    public User login(String email, String password) {
        return userRepo.findByEmail(email)
                .filter(u -> encoder.matches(password, u.getPassword()))
                .orElse(null);
    }
}
