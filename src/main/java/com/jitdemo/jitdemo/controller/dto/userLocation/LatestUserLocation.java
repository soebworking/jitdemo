package com.jitdemo.jitdemo.controller.dto.userLocation;

import com.jitdemo.jitdemo.controller.dto.mobileLocation.Location;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

//https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#projections

public interface LatestUserLocation {
    String getUserId();
    Date getLocationCreatedOn();
    String getEmail();
    String getFirstName();
    String getSecondName();
    Double getLatitude();
    Double getLongitude();

}
