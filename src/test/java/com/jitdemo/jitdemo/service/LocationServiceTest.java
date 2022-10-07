package com.jitdemo.jitdemo.service;

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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class LocationServiceTest {

    @Mock
    private LocationRepository locationRepository;

    private UUID uid = UUID.randomUUID();
    @InjectMocks
    private LocationService locationService;

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

        locationService.getUserLocationsFromDates(UUID.randomUUID(), date1, date2);

        //System.out.println("== " + responseEntity.getStatusCode());
    }
}
