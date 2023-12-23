package com.solvd.laba.hospital.service;

import com.solvd.laba.hospital.model.person.PatientPerson;

import java.util.List;
import java.util.Optional;

public interface PatientService {
    PatientPerson add(PatientPerson patient);

    List<PatientPerson> getAll();

    Optional<PatientPerson> getById(long id);

    void update(PatientPerson patient);

    void delete(long id);
}
