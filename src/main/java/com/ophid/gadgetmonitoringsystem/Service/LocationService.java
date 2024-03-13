package com.ophid.gadgetmonitoringsystem.Service;

import com.ophid.gadgetmonitoringsystem.Entity.LocationLazarous;

import java.util.List;

public interface LocationService {
    String saveLocation(LocationLazarous location);

    List<LocationLazarous> getAllLocations();

    LocationLazarous getLocationByID(Long locationId);
}
