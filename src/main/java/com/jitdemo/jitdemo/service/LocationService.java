package com.jitdemo.jitdemo.service;

import com.jitdemo.jitdemo.model.Locations;
import com.jitdemo.jitdemo.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Service
@Transactional
public class LocationService {

    @Autowired
    private LocationRepository locationRepository;

    public void saveLocation(Locations locations){
        locationRepository.save(locations);
    }


    public String getUserLocationsFromDates(UUID uuid, String fromDate, String toDate) throws ParseException {
        System.out.println("Inside Service");

        //first check with date is lower
        Date fromDateSql = null;
        Date toDateSql = null;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = sdf.parse(fromDate);
        Date date2 = sdf.parse(toDate);


        int result = date1.compareTo(date2);

        //if fromDate < toDate then no change/swap needed
        fromDateSql = sdf.parse(fromDate);
        toDateSql = sdf.parse(toDate);

        if (result > 0) {
            fromDateSql = sdf.parse(toDate);
            toDateSql = sdf.parse(fromDate);
        }
        System.out.println("From date : " + fromDateSql);
        System.out.println("To date : " + toDateSql);
        locationRepository.getAllBetweenDates(uuid, fromDateSql, toDateSql);
        return null;
    }
}
