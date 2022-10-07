package com.jitdemo.jitdemo.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jitdemo.jitdemo.controller.dto.userLocation.JsonLatestUserLocation;
import com.jitdemo.jitdemo.controller.dto.userLocation.LatestUserLocation;
import com.jitdemo.jitdemo.controller.dto.userLocation.LatestUserLocationMapping;
import com.jitdemo.jitdemo.exception.UserNotFoundException;
import com.jitdemo.jitdemo.model.User;
import com.jitdemo.jitdemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public String checkUserByEmail(String email) throws UserNotFoundException {
        if(userRepository.existsByEmail(email).isEmpty()){
            throw new UserNotFoundException("User with email " + email + " does not exist");
        }
        return userRepository.existsByEmail(email) ;
    }

    // not implemented exception as we create a new user and update if existing
    public void userSave(User user){
        userRepository.save(user);

    }

    //not implemented exception as we return boolean and calling class logic works based on boolean
    public boolean checkUserById(UUID uuid){
        return userRepository.findById(uuid).isPresent();
    }


    //not implemented exception as we return Optional<User> and calling class handle the logic
    public Optional<User> getUserById(UUID uuid){
        return userRepository.findById(uuid);
    }


    //Since we're getting request from mobile (logged-in user), we assume will have always valid UUID
    public String getLatestUserLocationById(UUID uuid) throws UserNotFoundException, JsonProcessingException {

        String jsonInString = getString(uuid);

        return jsonInString;
    }






    private String getString(UUID uuid) throws JsonProcessingException {
        LatestUserLocation latestUserLocation = userRepository.getLatestUserLocationById(uuid.toString());
        JsonLatestUserLocation jsonLatestUserLocation = new JsonLatestUserLocation();
        if(null != latestUserLocation){


            jsonLatestUserLocation.setUserId(latestUserLocation.getUserId());
            jsonLatestUserLocation.setCreatedOn(latestUserLocation.getLocationCreatedOn().toString());
            jsonLatestUserLocation.setEmail(latestUserLocation.getEmail());
            jsonLatestUserLocation.setFirstName(latestUserLocation.getFirstName());
            jsonLatestUserLocation.setSecondName(latestUserLocation.getSecondName());

            LatestUserLocationMapping userMobilelocation = new LatestUserLocationMapping(
                    String.valueOf(latestUserLocation.getLatitude()),
                    String.valueOf(latestUserLocation.getLongitude())
            );
            jsonLatestUserLocation.setLocation(userMobilelocation);

        }


        ObjectMapper mapper = new ObjectMapper();
        //Convert object to JSON string
        String jsonInString = mapper.writeValueAsString(jsonLatestUserLocation);
        return jsonInString;
    }

}
