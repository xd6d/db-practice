package com.solvd.laba.model.person;

import lombok.Getter;

@Getter
public enum Position {
    RECEPTIONIST("Receptionist", false),
    DOCTOR("Doctor", true),
    NURSE("Nurse", false);

    private final String name;
    private final boolean isDoctor;

    Position(String name, boolean isDoctor) {
        this.name = name;
        this.isDoctor = isDoctor;
    }
}
