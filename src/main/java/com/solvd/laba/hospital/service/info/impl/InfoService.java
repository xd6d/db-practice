package com.solvd.laba.hospital.service.info.impl;

import com.solvd.laba.hospital.dao.repository.person.PatientRepository;
import com.solvd.laba.hospital.dao.repository.person.impl.PatientRepositoryMybatisImpl;
import com.solvd.laba.hospital.model.exceptions.IncorrectInfoException;

public abstract class InfoService {
    private final PatientRepository patientRepository = new PatientRepositoryMybatisImpl();

    void validate(long patientId) throws IncorrectInfoException {
        if (patientRepository.findById(patientId).isEmpty()) {
            throw new IncorrectInfoException("Patient with given id does not exist");
        }
    }
}
