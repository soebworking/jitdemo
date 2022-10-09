package com.jitdemo.jitdemo.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jitdemo.jitdemo.controller.dto.userLocationsByDates.Location;
import com.jitdemo.jitdemo.controller.dto.userLocationsByDates.UserLocationQuery;
import com.jitdemo.jitdemo.controller.dto.userLocationsByDates.UserLocationsByDates;
import com.jitdemo.jitdemo.model.Locations;
import com.jitdemo.jitdemo.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class LocationService {

    @Autowired
    private LocationRepository locationRepository;

    public void saveLocation(Locations locations){
        locationRepository.save(locations);
    }


    public String getUserLocationsFromDates(UUID uuid, String fromDate, String toDate) throws ParseException, JsonProcessingException {

        String jsonInString = null;
        List<UserLocationQuery> locationResults =  locationRepository.getAllBetweenDates(uuid.toString(), fromDate, toDate);

        List<Location> userLocation = new ArrayList<Location>(); // latitude, longitude
        List<com.jitdemo.jitdemo.controller.dto.userLocationsByDates.Locations> locationsCreatedOn = new ArrayList<com.jitdemo.jitdemo.controller.dto.userLocationsByDates.Locations>(); // created on


        for (UserLocationQuery tempLocationResults : locationResults) {
            Location ulLocation  = new Location(tempLocationResults.getLatitude(),tempLocationResults.getLongitude() );
            userLocation.add(ulLocation);

            com.jitdemo.jitdemo.controller.dto.userLocationsByDates.Locations ulLocations = new com.jitdemo.jitdemo.controller.dto.userLocationsByDates.Locations(
                    tempLocationResults.getLocationCreatedOn(),
                    ulLocation
            );

            locationsCreatedOn.add(ulLocations);

            UserLocationsByDates ulUserLocationsByDates =  new UserLocationsByDates(
                    tempLocationResults.getUserId(),
                    locationsCreatedOn

            );


            ObjectMapper mapper = new ObjectMapper();
            //Convert object to JSON string
            jsonInString = mapper.writeValueAsString(ulUserLocationsByDates);

        }

        return jsonInString;
    }
}
