package com.solvd.laba.hospital.model.person;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlEnumValue;
import lombok.Getter;

@Getter
@XmlAccessorType(XmlAccessType.FIELD)
public enum Position {
    @XmlEnumValue("Receptionist") RECEPTIONIST("Receptionist", false),
    @XmlEnumValue("Doctor") DOCTOR("Doctor", true),
    @XmlEnumValue("Nurse") NURSE("Nurse", false);

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
