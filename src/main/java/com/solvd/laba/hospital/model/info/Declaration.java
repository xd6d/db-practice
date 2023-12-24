package com.solvd.laba.hospital.model.info;

import com.solvd.laba.hospital.model.person.EmployeePerson;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(of = "id")
public class Declaration {
    private long id;
    private EmployeePerson doctor;
    private Date created;
    private Date expires;
}
