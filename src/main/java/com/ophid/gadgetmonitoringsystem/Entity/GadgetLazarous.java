package com.ophid.gadgetmonitoringsystem.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class GadgetLazarous {

    @Id
    private String serialNumber;
    private String name;
    private String description;
    private String model;
    private int batteryLevel;
    private Status status;
    private LocalDateTime dateIssuedToEmployee;
    private String image;

    @ManyToOne
    private LocationLazarous location;

    @ManyToMany
    private List<GadgetMaintananceHistoryLazarous> maintananceHistories;





}
