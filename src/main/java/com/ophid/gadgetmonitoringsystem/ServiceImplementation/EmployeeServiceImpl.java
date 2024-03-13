package com.ophid.gadgetmonitoringsystem.ServiceImplementation;

import com.ophid.gadgetmonitoringsystem.Entity.EmployeeLazarous;
import com.ophid.gadgetmonitoringsystem.Repository.EmployeeRepository;
import com.ophid.gadgetmonitoringsystem.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public String saveEmployee(EmployeeLazarous employee) {
        employeeRepository.save(employee);

        return "Employee saved successfully";
    }

    @Override
    public EmployeeLazarous findEmployee(String employeeId) {
        return employeeRepository.findByEmpId(employeeId);
    }

    @Override
    public List<EmployeeLazarous> getEmployees() {
        return employeeRepository.findAll();
    }
}
