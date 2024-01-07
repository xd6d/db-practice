package com.solvd.laba.hospital.model.info;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(of = "id")
@XmlAccessorType(XmlAccessType.FIELD)
public class Vaccination {
    @XmlAttribute
    private long id;
    private String name;
    private Date date;
    private Date expires;
}
