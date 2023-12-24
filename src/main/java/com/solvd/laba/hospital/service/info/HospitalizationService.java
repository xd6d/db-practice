package com.solvd.laba.hospital.service.info;

import com.solvd.laba.hospital.model.info.Hospitalization;

import java.util.List;

public interface HospitalizationService {
    List<Hospitalization> getAllByPatientId(long id);

    Hospitalization add(Hospitalization hospitalization, long patientId);
}
