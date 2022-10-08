package com.jitdemo.jitdemo.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jitdemo.jitdemo.controller.dto.mobileLocation.MobiileUser;
import com.jitdemo.jitdemo.model.Locations;
import com.jitdemo.jitdemo.model.User;
import com.jitdemo.jitdemo.service.LocationService;
import com.jitdemo.jitdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Optional;
import java.util.UUID;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private LocationService locationService;

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
            }else {
                //create new user
                userService.userSave(user);
                response =  ResponseEntity.status(HttpStatus.CREATED).body("User " + user.getEmail() + " successfully created");
            }
        }catch(Exception e){
            response =  ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("User " + user.getEmail() + " failed");

        }

        return response;
    }



    //funcation to handle mobile request
    @PostMapping("/user/add/mobileLoc")
    public ResponseEntity<String> addMobiileLocation(@RequestBody String userMobileLocation) throws JsonProcessingException {

        ResponseEntity<String> response = null;
        try{


            ObjectMapper objectMapper = new ObjectMapper();
            MobiileUser mobiileUser = objectMapper.readValue(userMobileLocation, MobiileUser.class);

            //first check whether given id exist or not
            UUID userId = UUID.fromString(mobiileUser.getUserId()); // wrapper since we're receiving Json as String
            boolean userExist = userService.checkUserById(userId);

            if(userExist) {

                Optional<User> userLocation = userService.getUserById(userId);
                User userLoc = userLocation.get();

                User existingUser = new User(userLoc.getUserId(),userLoc.getCreatedOn()
                        ,userLoc.getEmail(), userLoc.getFirstName(), userLoc.getSecondName());

                //user exist so insert records to database
                Locations locations = new Locations();
                locations.setLocationCreatedOn(mobiileUser.getCreatedOn());
                locations.setLatitude(mobiileUser.getLocation().getLatitude());
                locations.setLongitude(mobiileUser.getLocation().getLongitude());
                locations.setUser(existingUser);
                locationService.saveLocation(locations);

                response =  ResponseEntity.status(HttpStatus.CREATED).body("Location for User id " + mobiileUser.getUserId() + " successfully created");

            }else {
                response = ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body("User " + userId+ " doesn't exist.");
            }

        }catch(Exception e){
            response =  ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("User mobile location insertion failed");
        }

        return response;

    }



    //Ger user's details per userId
    @GetMapping("/user/getLocation/latest/{userId}")
    @ResponseBody
    public ResponseEntity<String> getLatestUserLocation(@PathVariable("userId") UUID userId) throws JsonProcessingException {

        ResponseEntity<String> response = null;
        String userLatestLocationJsonStr = null;

        try{
            userLatestLocationJsonStr = userService.getLatestUserLocationById(userId);
            response =  ResponseEntity.status(HttpStatus.OK).body(userLatestLocationJsonStr);
        }catch(Exception e){
            response =  ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("User latest mobile location failed");
        }

        return response;
    }


    @GetMapping("/user/getLocation/fromDates/{userId}/{fromDate}/{toDate}")
    @ResponseBody
    public ResponseEntity<String> getUserLocationsFromDates(@PathVariable("userId") UUID userId, @PathVariable("fromDate") String fromDate, @PathVariable("toDate") String toDate) throws ParseException, JsonProcessingException {
        ResponseEntity<String> response = null;
        String userLocationsJsonStr = null;
        try{
            userLocationsJsonStr = locationService.getUserLocationsFromDates(userId, fromDate, toDate);
            response =  ResponseEntity.status(HttpStatus.OK).body(userLocationsJsonStr);
        }catch(Exception e){
            response =  ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("User mobile location failed");
        }

        return response;

    }

}
