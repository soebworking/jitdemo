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
import java.text.ParseException;
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
    public void getUserLocationsFromDates() throws ParseException {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        //TODO:XXX

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = sdf.parse("2020-03-30");
        Date date2 = sdf.parse("2020-01-31");

        System.out.println("date1 : " + sdf.format(date1));
        System.out.println("date2 : " + sdf.format(date2));

        int result = date1.compareTo(date2);
        System.out.println("result: " + result);

        if (result == 0) {
            System.out.println("Date1 is equal to Date2");
        } else if (result > 0) {
            System.out.println("Date1 is after Date2"); //Date1 > Date2
        } else if (result < 0) {
            System.out.println("Date1 is before Date2"); // Date1 < Date2
        }

        ResponseEntity<?> responseEntity = userController.getUserLocationsFromDates(UUID.randomUUID(), date1, date2);

        System.out.println("== " + responseEntity.getStatusCode());
    }


}