package com.jitdemo.jitdemo.controller.dto.userLocationsByDates;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Locations {
    private String createdOn;
    private Location location;
}
