package com.solvd.laba.hospital.dao.repository;

import com.solvd.laba.hospital.model.person.PatientPerson;

import java.util.List;
import java.util.Optional;

public interface PatientRepository {
    PatientPerson create(PatientPerson patient);

    List<PatientPerson> findAll();

    Optional<PatientPerson> findById(long id);

    void update(PatientPerson patient);

    void deleteById(long id);
}
