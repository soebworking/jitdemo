package com.jitdemo.jitdemo.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jitdemo.jitdemo.model.User;
import com.jitdemo.jitdemo.repository.UserRepository;
import com.jitdemo.jitdemo.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;



@ExtendWith(MockitoExtension.class)
class UserControllerTest {
    @InjectMocks
    UserController userController;

    @Mock
    UserService userService;

    @Mock
    UserRepository userRepository;
    ObjectMapper objectMapper = new ObjectMapper();



    @Test
    void addUserNew() throws Exception {

        User user = new User();
        String dDate="2022-02-08T11:44:00.524";
        DateFormat df = new SimpleDateFormat("yyy-MM-dd'T'HH:mm:ss.S");
        Date cDate = df.parse(dDate);

        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        User userToAdd = new User(null, cDate, "junit@test.com",
                "junitFirstName", "junitSecondName" );

        ResponseEntity<?> responseEntity = userController.addUser(userToAdd);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(201);

    }



    @Test
    void addUserExisting() throws Exception {

        User user = new User();
        String dDate="2022-02-08T11:44:00.524";
        DateFormat df = new SimpleDateFormat("yyy-MM-dd'T'HH:mm:ss.S");
        Date cDate = df.parse(dDate);

        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        User userToAdd = new User(UUID.randomUUID(), cDate, "junit@test.com",
                "junitFirstName", "junitSecondName" );

        ResponseEntity<?> responseEntity = userController.addUser(userToAdd);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(201);

    }


    @Test
    void addMobiileLocation() throws Exception{

        String userMobileLocation = "{" +
                "    \"userId\": \"d5b04122-b1a7-4047-80ae-7fa97ce34b4b\"," +
                "    \"createdOn\": \"2022-02-08T11:44:00.524\"," +
                "    \"location\": " +
                "    {" +
                "        \"latitude\": 52.25742342295784," +
                "        \"longitude\": 10.540583401747602 " +
                "    }" +
                "}";


        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        ResponseEntity<?> responseEntity = userController.addMobiileLocation(userMobileLocation);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(208);

    }

    @Test
    public void getLatestUserLocation() throws JsonProcessingException {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        ResponseEntity<?> responseEntity = userController.getLatestUserLocation(UUID.randomUUID());
        assertThat(responseEntity.getStatusCode()).isEqualTo(208);
    }

    @Test
    public void getUserLocationsFromDates(){
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        //TODO:XXX

    }


}