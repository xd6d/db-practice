package com.solvd.laba.hospital.model.person;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "id")
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class Person {
    @XmlAttribute
    private long id;
    private String name;
    private String surname;
    private String phoneNumber;
    private String email;
}
