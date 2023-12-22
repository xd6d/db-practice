package com.solvd.laba.hospital.model.appointment;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "id")
public class Service {
    private long id;
    private String name;
    private double price;
    private String description;
}
