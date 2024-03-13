package com.ophid.gadgetmonitoringsystem.Repository;

import com.ophid.gadgetmonitoringsystem.Entity.GadgetLazarous;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GadgetRepository extends JpaRepository<GadgetLazarous, String> {

}
