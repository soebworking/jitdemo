package com.jitdemo.jitdemo.controller.dto.mobileLocation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class MobiileUser {
    private String userId;
    private Date createdOn;
    private Location location;
}
