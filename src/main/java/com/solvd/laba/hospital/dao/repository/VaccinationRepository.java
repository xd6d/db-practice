package com.solvd.laba.hospital.dao.repository;

import com.solvd.laba.hospital.model.info.Vaccination;

import java.util.List;

public interface VaccinationRepository {
    List<Vaccination> findAllByPatientId(long id);
}