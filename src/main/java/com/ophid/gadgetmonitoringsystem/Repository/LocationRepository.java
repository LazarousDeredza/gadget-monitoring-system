package com.ophid.gadgetmonitoringsystem.Repository;

import com.ophid.gadgetmonitoringsystem.Entity.LocationLazarous;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends JpaRepository<LocationLazarous,Long> {
}
