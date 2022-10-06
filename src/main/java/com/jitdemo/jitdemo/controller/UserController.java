package com.jitdemo.jitdemo.controller;

import com.jitdemo.jitdemo.model.User;
import com.jitdemo.jitdemo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;


    @PostMapping("/user/add")
    public ResponseEntity<?> addUser(@RequestBody User user){
        ResponseEntity<?> response = null;


        return response;
    }






}
