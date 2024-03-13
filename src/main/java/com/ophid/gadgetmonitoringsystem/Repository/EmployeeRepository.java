package com.ophid.gadgetmonitoringsystem.Repository;

import com.ophid.gadgetmonitoringsystem.Entity.EmployeeLazarous;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EmployeeRepository extends JpaRepository<EmployeeLazarous, String> {

  @Query("SELECT e FROM EmployeeLazarous e WHERE e.employeeId = ?1")
  EmployeeLazarous findByEmpId(String employeeId);
}
