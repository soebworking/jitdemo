package com.jitdemo.jitdemo.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jitdemo.jitdemo.model.Locations;
import com.jitdemo.jitdemo.model.User;
import com.jitdemo.jitdemo.repository.LocationRepository;
import com.jitdemo.jitdemo.repository.UserRepository;
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
import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.internal.bytebuddy.implementation.FixedValue.nullValue;
import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LocationServiceTest {

    @Mock
    private LocationRepository locationRepository;

    private UUID uid = UUID.randomUUID();
    @InjectMocks
    private LocationService locationService;

    @Test
    public void getUserLocationsFromDates() throws ParseException, JsonProcessingException {

        String dDate="2022-02-08T11:44:00.524";
        DateFormat df = new SimpleDateFormat("yyy-MM-dd'T'HH:mm:ss.S");
        Date creationDate = df.parse(dDate);

        String fromDate = "2020-03-30T11:44:00.524";
        String toDate = "2020-09-31T11:44:00.524";

        Date fDate = df.parse(fromDate);
        Date tDate = df.parse(toDate);

        User userToAdd = new User(null, fromDate, "junit@test.com",
                "junitFirstName", "junitSecondName" );

        Locations locations = new Locations(1, fromDate, 27.540583401747602 , 26.540583401747602, userToAdd );
        List<Locations> locationsList = new ArrayList<Locations>();
        locationsList.add(locations);

        String str;
        str = locationService.getUserLocationsFromDates(UUID.randomUUID(), fromDate, toDate);
        assertThat(str).isEqualTo(null);
    }
}
