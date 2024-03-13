package com.ophid.gadgetmonitoringsystem.Controller;

import com.ophid.gadgetmonitoringsystem.Entity.EmployeeLazarous;
import com.ophid.gadgetmonitoringsystem.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/saveEmployee")
    public String saveEmployee(@RequestBody EmployeeLazarous employee) {
        EmployeeLazarous employee1 = employeeService.findEmployee(employee.getEmployeeId());

        if (employee1 != null) {
            return "Employee already exists";
        }

       return employeeService.saveEmployee(employee);
    }

    @GetMapping("/getEmployees")
    public List<EmployeeLazarous> getEmployees() {
        return employeeService.getEmployees();
    }
}
