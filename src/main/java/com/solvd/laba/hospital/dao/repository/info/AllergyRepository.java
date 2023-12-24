package com.solvd.laba.hospital.dao.repository.info;

import com.solvd.laba.hospital.model.info.Allergy;

import java.util.List;

public interface AllergyRepository {
    List<Allergy> findAllByPatientId(long id);

    Allergy create(Allergy allergy, long patientId);
}
