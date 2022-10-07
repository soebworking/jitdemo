package com.jitdemo.jitdemo.controller.dto.userLocation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class JsonLatestUserLocation {
    private String userId;
    private String createdOn;
    private String email;
    private String firstName;
    private String secondName;
    private LatestUserLocationMapping location;
}
