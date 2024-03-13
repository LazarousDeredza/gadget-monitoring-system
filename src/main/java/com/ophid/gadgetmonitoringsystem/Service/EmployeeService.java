package com.ophid.gadgetmonitoringsystem.Service;

import com.ophid.gadgetmonitoringsystem.Entity.EmployeeLazarous;

import java.util.List;

public interface EmployeeService {
    String saveEmployee(EmployeeLazarous employee);

    EmployeeLazarous findEmployee(String employeeId);

    List<EmployeeLazarous> getEmployees();
}
