package com.solvd.laba.hospital.dao;

import com.solvd.laba.hospital.model.person.PatientPerson;

import java.util.List;
import java.util.Optional;

public interface PatientRepository {
    List<PatientPerson> getAll();

    Optional<PatientPerson> findById(long id);


}
