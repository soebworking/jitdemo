package com.jitdemo.jitdemo.service;

import com.jitdemo.jitdemo.model.User;
import com.jitdemo.jitdemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

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

    public boolean checkUserById(UUID uuid){
        return userRepository.findById(uuid).isPresent();
    }

}
