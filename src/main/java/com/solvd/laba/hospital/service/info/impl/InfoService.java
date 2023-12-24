package com.solvd.laba.hospital.service.info.impl;

import com.solvd.laba.hospital.model.exceptions.IncorrectInfoException;
import com.solvd.laba.hospital.service.person.PatientService;

public abstract class InfoService {
    private final PatientService patientService;

    protected InfoService(PatientService patientService) {
        this.patientService = patientService;
    }


    void validate(long patientId) throws IncorrectInfoException {
        if (patientService.getById(patientId).isEmpty()) {
            throw new IncorrectInfoException("Patient with given id does not exist");
        }
    }
}
