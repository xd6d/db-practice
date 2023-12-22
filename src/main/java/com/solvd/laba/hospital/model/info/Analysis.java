package com.solvd.laba.hospital.model.info;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "id")
public class Analysis {
    private long id;
    private String name;
    private String value;
    private String unit;
    private String healthyValue;
}
