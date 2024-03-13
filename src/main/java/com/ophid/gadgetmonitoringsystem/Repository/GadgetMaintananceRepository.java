package com.ophid.gadgetmonitoringsystem.Repository;

import com.ophid.gadgetmonitoringsystem.Entity.GadgetMaintananceHistoryLazarous;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GadgetMaintananceRepository extends JpaRepository<GadgetMaintananceHistoryLazarous, Long> {
}
