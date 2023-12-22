package com.solvd.laba.model.info;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(of = "id")
public class Vaccination {
    private long id;
    private String name;
    private Date date;
    private Date expires;
}
