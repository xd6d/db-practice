package com.solvd.laba.hospital.dao.repository.info;

import com.solvd.laba.hospital.model.info.Declaration;

import java.util.Optional;

public interface DeclarationRepository {
    Optional<Declaration> findByPatientId(long id);

    Declaration create(Declaration declaration, long patientId);

    void update(Declaration declaration);

    void deleteById(long id);
}
