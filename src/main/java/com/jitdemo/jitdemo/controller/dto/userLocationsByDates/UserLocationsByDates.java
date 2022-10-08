package com.jitdemo.jitdemo.controller.dto.userLocationsByDates;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserLocationsByDates {

    private String userId;
    private List<Locations> locations = new ArrayList<Locations>();

}
