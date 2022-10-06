package com.jitdemo.jitdemo.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jitdemo.jitdemo.controller.dto.mobileLocation.MobiileUser;
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

import java.util.Optional;
import java.util.UUID;

@RestController
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;


    //funcation to create OR update user
    @PostMapping("/user/add")
    public ResponseEntity<String> addUser(@RequestBody User user){
        ResponseEntity<String> response = null;
        String userId = userService.checkUserByEmail(user.getEmail());

        try{
            //first check whether user exists or not
            if(null != userId && userId.equalsIgnoreCase("true") ){
                //user already exist
                User updateUser = new User();
                updateUser.setUserId(user.getUserId());
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



    //funcation to handle mobile request
    @PostMapping("/user/add/mobileLoc")
    public ResponseEntity<String> addMobiileLocation(@RequestBody String userMobileLocation) throws JsonProcessingException {
        ResponseEntity<String> responseEntity = null;
        ObjectMapper objectMapper = new ObjectMapper();
        MobiileUser mobiileUser = objectMapper.readValue(userMobileLocation, MobiileUser.class);
        //first check whether given id exist or not
        logger.info(" => " + userMobileLocation);
        logger.info(" => " + mobiileUser.getLocation().getLongitude());


        UUID userId = UUID.fromString(mobiileUser.getUserId()); // wrapper since we're receiving Json as String
        boolean userExist = userService.checkUserById(userId);

        if(userExist){
            //user exist so insert records to database



        }else {
            logger.info("user {} not found in the database", userId);
        }
        return responseEntity;


    }




}
