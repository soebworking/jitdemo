package com.jitdemo.jitdemo.controller;

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

        User usertoAdd = new User(null, cDate, "junit@test.com",
                "junitFirstName", "junitSecondName" );

        // Creating Object of ObjectMapper define in Jackson API
        ObjectMapper Obj = new ObjectMapper();

        // Converting the Java object into a JSON string
        String jsonStr = Obj.writeValueAsString(usertoAdd);

        ResponseEntity<?> responseEntity = userController.addUser(usertoAdd);
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

        User usertoAdd = new User(UUID.randomUUID(), cDate, "junit@test.com",
                "junitFirstName", "junitSecondName" );

        // Creating Object of ObjectMapper define in Jackson API
        ObjectMapper Obj = new ObjectMapper();

        // Converting the Java object into a JSON string
        String jsonStr = Obj.writeValueAsString(usertoAdd);

        ResponseEntity<?> responseEntity = userController.addUser(usertoAdd);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(201);

    }


}