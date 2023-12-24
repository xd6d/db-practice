package com.solvd.laba.hospital.service.info;

import com.solvd.laba.hospital.model.info.Vaccination;

import java.util.List;

public interface VaccinationService {
    List<Vaccination> getAllByPatientId(long id);

    Vaccination add(Vaccination vaccination, long patientId);
}
