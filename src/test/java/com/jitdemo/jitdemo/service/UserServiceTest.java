package com.jitdemo.jitdemo.service;

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

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

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



}
