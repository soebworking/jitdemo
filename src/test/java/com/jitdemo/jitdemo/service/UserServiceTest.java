package com.jitdemo.jitdemo.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jitdemo.jitdemo.controller.dto.userLocation.JsonLatestUserLocation;
import com.jitdemo.jitdemo.controller.dto.userLocation.LatestUserLocation;
import com.jitdemo.jitdemo.controller.dto.userLocation.LatestUserLocationMapping;
import com.jitdemo.jitdemo.exception.UserNotFoundException;
import com.jitdemo.jitdemo.model.User;
import com.jitdemo.jitdemo.repository.UserRepository;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    private UUID uid = UUID.randomUUID();
    @InjectMocks
    private UserService userService;


    @Test
    public void userSave() throws ParseException {
        String dDate="2022-02-08T11:44:00.524";
        DateFormat df = new SimpleDateFormat("yyy-MM-dd'T'HH:mm:ss.S");
        Date cDate = df.parse(dDate);
        User userToAdd = new User(null, cDate, "junit@test.com",
                "junitFirstName", "junitSecondName" );
        when(userRepository.save(userToAdd)).thenReturn(userToAdd);

        User savedUser = userRepository.save(userToAdd);
        assertThat(savedUser.getEmail()).isNotNull();
    }


    @Test
    public void checkUserByEmail() throws ParseException {

        String email = "junit@test.com";
        when(userRepository.existsByEmail(email)).thenReturn(email);
        assertThat(userService.checkUserByEmail(email)).isEqualTo(email);

    }


    @Test
    public void checkUserById(){

        boolean val = userService.checkUserById(uid);
        assertFalse( val );

    }

    @Test
    public void getUserById() throws ParseException {

        String dDate="2022-02-08T11:44:00.524";
        DateFormat df = new SimpleDateFormat("yyy-MM-dd'T'HH:mm:ss.S");
        Date cDate = df.parse(dDate);
        User userToAdd = new User(null, cDate, "junit@test.com",
                "junitFirstName", "junitSecondName" );
        when(userRepository.findById(uid)).thenReturn(Optional.of(userToAdd));

        Optional<User> userOptional = userService.getUserById(uid);
        assertThat(userOptional.get().getEmail()).isEqualTo("junit@test.com");

    }


    @Test
    public void getLatestUserLocation() throws JsonProcessingException {

        //TODO:Open

        Exception exception = assertThrows(UserNotFoundException.class, () -> {
            userService.getLatestUserLocationById(uid);
        });

        String expectedMessage = "User ID: " + uid + "does not exist";
        String actualMessage = exception.getMessage();

       // assertTrue(actualMessage.contains(expectedMessage));


    }


    @Test
    public void getUserLocationsFromDates() throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = sdf.parse("2020-03-30");
        Date date2 = sdf.parse("2020-01-31");

       // System.out.println("date1 : " + sdf.format(date1));
       // System.out.println("date2 : " + sdf.format(date2));

        int result = date1.compareTo(date2);
        System.out.println("result: " + result);

        if (result == 0) {
            System.out.println("Date1 is equal to Date2");
        } else if (result > 0) {
            System.out.println("Date1 is after Date2"); //Date1 > Date2
        } else if (result < 0) {
            System.out.println("Date1 is before Date2"); // Date1 < Date2
        }

        userService.getUserLocationsFromDates(UUID.randomUUID(), date1, date2);


    }

}
