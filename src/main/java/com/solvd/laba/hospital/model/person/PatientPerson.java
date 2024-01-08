package com.solvd.laba.hospital.model.person;

import com.solvd.laba.hospital.model.info.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString(callSuper = true)
public class PatientPerson extends Person {
    private Declaration declaration;
    private List<Analysis> analyses = new ArrayList<>();
    private List<Vaccination> vaccinations = new ArrayList<>();
    private List<Hospitalization> hospitalizations = new ArrayList<>();
    private List<Allergy> allergies = new ArrayList<>();
}
