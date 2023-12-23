package com.solvd.laba.hospital.dao.repository;

import com.solvd.laba.hospital.model.info.Hospitalization;

import java.util.List;

public interface HospitalizationRepository {
    List<Hospitalization> findAllByPatientId(long id);
}
