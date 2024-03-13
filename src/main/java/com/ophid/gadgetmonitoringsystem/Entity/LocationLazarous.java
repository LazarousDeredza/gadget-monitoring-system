package com.ophid.gadgetmonitoringsystem.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;


@Entity
@Data
public class LocationLazarous {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String districtName;
    private String province;
}
