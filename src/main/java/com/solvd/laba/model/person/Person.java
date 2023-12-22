package com.solvd.laba.model.person;

import lombok.*;

@Data
@EqualsAndHashCode(of = "id")
public abstract class Person {
    private long id;
    private String name;
    private String surname;
    private String phoneNumber;
    private String email;
}
