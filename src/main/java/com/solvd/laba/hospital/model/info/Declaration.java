package com.solvd.laba.hospital.model.info;

import com.solvd.laba.hospital.model.person.EmployeePerson;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(of = "id")
@XmlAccessorType(XmlAccessType.FIELD)
public class Declaration {
    @XmlAttribute
    private long id;
    private EmployeePerson doctor;
    private Date created;
    private Date expires;
}
