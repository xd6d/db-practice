package com.solvd.laba.hospital.model.person;

import com.solvd.laba.hospital.model.info.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString(callSuper = true)
public class PatientPerson extends Person {
    private Declaration declaration;
    private List<Analysis> analyses;
    private List<Vaccination> vaccinations;
    private List<Hospitalization> hospitalizations;
    private List<Allergy> allergies;
}
