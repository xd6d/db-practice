package com.solvd.laba.hospital.service;

import com.solvd.laba.hospital.model.info.Hospitalization;

import java.util.List;

public interface HospitalizationService {
    List<Hospitalization> getAllByPatientId(long id);
}
