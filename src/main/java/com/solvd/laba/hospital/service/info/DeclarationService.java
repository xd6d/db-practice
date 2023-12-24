package com.solvd.laba.hospital.service.info;

import com.solvd.laba.hospital.model.info.Declaration;

public interface DeclarationService {
    Declaration getByPatientId(long id);

    Declaration add(Declaration declaration, long patientId);

    void update(Declaration declaration);

    void deleteById(long id);
}
