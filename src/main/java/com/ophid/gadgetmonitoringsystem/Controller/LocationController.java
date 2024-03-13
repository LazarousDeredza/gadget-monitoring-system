package com.ophid.gadgetmonitoringsystem.Controller;

import com.ophid.gadgetmonitoringsystem.Entity.LocationLazarous;
import com.ophid.gadgetmonitoringsystem.Service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LocationController {

    @Autowired
    private LocationService locationService;

    @PostMapping("saveLocation")
    private String saveLocation(@RequestBody LocationLazarous location){

       return locationService.saveLocation(location);
    }
    @GetMapping("/getAllLocations")

    private List<LocationLazarous> getLocations(){
        return locationService.getAllLocations();
    }
}
