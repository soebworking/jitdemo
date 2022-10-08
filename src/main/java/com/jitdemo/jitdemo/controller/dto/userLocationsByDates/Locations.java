package com.jitdemo.jitdemo.controller.dto.userLocationsByDates;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Locations {
    private Date createdOn;
    private Location location;
}
