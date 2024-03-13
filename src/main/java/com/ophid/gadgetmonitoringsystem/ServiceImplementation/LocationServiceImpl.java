package com.ophid.gadgetmonitoringsystem.ServiceImplementation;

import com.ophid.gadgetmonitoringsystem.Entity.LocationLazarous;
import com.ophid.gadgetmonitoringsystem.Repository.LocationRepository;
import com.ophid.gadgetmonitoringsystem.Service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationServiceImpl implements LocationService {
    @Autowired
    private LocationRepository locationRepository;

    @Override
    public String saveLocation(LocationLazarous location) {
        locationRepository.save(location);
        return "Location Saved Successfully";
    }

    @Override
    public List<LocationLazarous> getAllLocations() {
        return locationRepository.findAll();
    }

    @Override
    public LocationLazarous getLocationByID(Long locationId) {
        return locationRepository.findById(locationId).orElseThrow(() -> new IllegalStateException("Location not found"));
    }
}
