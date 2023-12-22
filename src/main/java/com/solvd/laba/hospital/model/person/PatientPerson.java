package com.solvd.laba.hospital.model.person;

import com.solvd.laba.hospital.model.info.Analysis;
import com.solvd.laba.hospital.model.info.Declaration;
import com.solvd.laba.hospital.model.info.Vaccination;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class PatientPerson extends Person {
    private Declaration declaration;
    private List<Analysis> analyses;
    private List<Vaccination> vaccinations;
}
