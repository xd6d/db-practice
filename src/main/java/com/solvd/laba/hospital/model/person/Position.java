package com.solvd.laba.hospital.model.person;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public enum Position {
    @JsonProperty("Receptionist") RECEPTIONIST("Receptionist", false),
    @JsonProperty("Doctor") DOCTOR("Doctor", true),
    @JsonProperty("Nurse") NURSE("Nurse", false);

    private final String name;
    private final boolean isDoctor;

    Position(String name, boolean isDoctor) {
        this.name = name;
        this.isDoctor = isDoctor;
    }

    public static Position fromName(String name) {
        for (Position p : Position.values()) {
            if (p.getName().equals(name)) {
                return p;
            }
        }
        return null;
    }
}
