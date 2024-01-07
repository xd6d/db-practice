package com.solvd.laba.hospital.model.person;

import com.solvd.laba.hospital.model.info.*;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString(callSuper = true)
@XmlRootElement(name = "patient")
public class PatientPerson extends Person {
    private Declaration declaration;

    @XmlElementWrapper(name = "analyses")
    @XmlElement(name = "analysis")
    private List<Analysis> analyses = new ArrayList<>();

    @XmlElementWrapper(name = "vaccinations")
    @XmlElement(name = "vaccination")
    private List<Vaccination> vaccinations = new ArrayList<>();

    @XmlElementWrapper(name = "hospitalizations")
    @XmlElement(name = "hospitalization")
    private List<Hospitalization> hospitalizations = new ArrayList<>();

    @XmlElementWrapper(name = "allergies")
    @XmlElement(name = "allergy")
    private List<Allergy> allergies = new ArrayList<>();
}
