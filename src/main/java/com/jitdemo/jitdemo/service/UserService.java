package com.jitdemo.jitdemo.service;

import com.jitdemo.jitdemo.controller.dto.userLocation.LatestUserLocation;
import com.jitdemo.jitdemo.exception.UserNotFoundException;
import com.jitdemo.jitdemo.model.User;
import com.jitdemo.jitdemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import javax.transaction.Transactional;
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


    /*
    Select  u.id, l.location_created_on,
    u.email, u.first_name, u.second_name,
    l.latitude, l.longitude
    from user as u inner join locations l
    on u.id = l.user_id
    Where
    u.id = 'ca93be79-7199-4084-ac5f-b4301fcae02c'
    And
        l.location_id = (
    Select l1.location_id
    From locations l1
    where l1.user_id = l.user_id
    order by l1.location_id desc
    limit 1 ); */
    public List<LatestUserLocation> getLatestUserLocationById(UUID uuid) throws UserNotFoundException{
        return userRepository.getLatestUserLocationById(uuid.toString());

    }
}
