package com.solvd.laba.hospital.model.info;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "id")
@XmlAccessorType(XmlAccessType.FIELD)
public class Allergy {
    @XmlAttribute
    private long id;
    private String name;
}
