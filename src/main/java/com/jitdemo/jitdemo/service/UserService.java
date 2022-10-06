package com.jitdemo.jitdemo.service;

import com.jitdemo.jitdemo.model.User;
import com.jitdemo.jitdemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public String checkUserByEmail(String email){
        return userRepository.existsByEmail(email) ;
    }

    public void userSave(User user){
        userRepository.save(user);

    }

}
