package com.solvd.laba.hospital.model.info;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(of = "id")
public class Hospitalization {
    private long id;
    private String place;
    private Date date;
    private String condition;
    private String description;
}
