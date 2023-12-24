package com.solvd.laba.hospital.service.info;

import com.solvd.laba.hospital.model.info.Allergy;

import java.util.List;

public interface AllergyService {

    List<Allergy> getAllByPatientId(long id);

    Allergy add(Allergy allergy, long patientId);
}
