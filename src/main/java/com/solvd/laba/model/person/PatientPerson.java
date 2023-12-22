package com.solvd.laba.model.person;

import com.solvd.laba.model.info.Analysis;
import com.solvd.laba.model.info.Declaration;
import com.solvd.laba.model.info.Vaccination;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PatientPerson extends Person {
    private Declaration declaration;
    private List<Analysis> analyses;
    private List<Vaccination> vaccinations;
}
