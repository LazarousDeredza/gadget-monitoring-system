package com.ophid.gadgetmonitoringsystem.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class EmployeeLazarous {

    @Id
    private String employeeId;
    private String employeeFirstName;
    private String employeeLastName;
    private String employeeUserName;

    @ManyToMany
    private List<GadgetLazarous> gadgetList;

}
