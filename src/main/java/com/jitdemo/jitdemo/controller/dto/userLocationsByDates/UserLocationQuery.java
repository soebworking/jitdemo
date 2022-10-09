package com.jitdemo.jitdemo.controller.dto.userLocationsByDates;

public interface UserLocationQuery {
    String getLocationCreatedOn();

    double getLatitude();

    double getLongitude();

    String getUserId();
}
