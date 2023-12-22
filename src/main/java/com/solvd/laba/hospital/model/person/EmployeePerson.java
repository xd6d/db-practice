package com.solvd.laba.hospital.model.person;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class EmployeePerson extends Person {
    private String degree;
    private Position position;
}
