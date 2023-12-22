package com.solvd.laba.model.appointment;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "id")
public class Medicine {
    private long id;
    private String name;
    private double price;
    private String description;
    private boolean isPrescribed;
}
