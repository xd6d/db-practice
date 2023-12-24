package com.solvd.laba.hospital.model.person;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "id")
public abstract class Person {
    private long id;
    private String name;
    private String surname;
    private String phoneNumber;
    private String email;
}
