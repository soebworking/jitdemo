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
    public ResponseEntity<String> addUser(@RequestBody User user){
        ResponseEntity<String> response = null;
        String userId = userService.checkUserByEmail(user.getEmail());

        try{
            //first check whether user exists or not
            if(null != userId && userId.equalsIgnoreCase("true") ){
                //user already exist
                User updateUser = new User();
                updateUser.setId(user.getId());
                updateUser.setCreatedOn(user.getCreatedOn());
                updateUser.setEmail(user.getEmail());
                updateUser.setFirstName(user.getFirstName());
                updateUser.setSecondName(user.getSecondName());
                userService.userSave(updateUser);
                response = ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body("User " + user.getEmail() + " successfully updated");
                logger.info("User successfully updated " + user.getEmail());
            }else {
                //create new user
                userService.userSave(user);
                response =  ResponseEntity.status(HttpStatus.CREATED).body("User " + user.getEmail() + " successfully created");
            }
        }catch(Exception e){
            logger.error("An ERROR occur while creating user.", e.getMessage());
            response =  ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("User " + user.getEmail() + " failed");

        }

        return response;
    }






}
