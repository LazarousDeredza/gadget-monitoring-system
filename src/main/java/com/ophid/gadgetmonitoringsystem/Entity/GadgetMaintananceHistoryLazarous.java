package com.ophid.gadgetmonitoringsystem.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class GadgetMaintananceHistoryLazarous {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private GadgetLazarous gadget;

    private LocalDateTime dateTime;
    private String title;
    private String description;
}
