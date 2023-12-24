package com.solvd.laba.hospital.dao.repository.info;

import com.solvd.laba.hospital.model.info.Hospitalization;

import java.util.List;

public interface HospitalizationRepository {
    List<Hospitalization> findAllByPatientId(long id);

    Hospitalization create(Hospitalization hospitalization, long patientId);
}
