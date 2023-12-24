package com.solvd.laba.hospital.model.info;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "id")
public class Allergy {
    private long id;
    private String name;
}
