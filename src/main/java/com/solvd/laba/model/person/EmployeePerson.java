package com.solvd.laba.model.person;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeePerson extends Person {
    private String degree;
    private Position position;
}
