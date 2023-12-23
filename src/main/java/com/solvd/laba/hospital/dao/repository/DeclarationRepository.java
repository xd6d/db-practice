package com.solvd.laba.hospital.dao.repository;

import com.solvd.laba.hospital.model.info.Declaration;

import java.util.Optional;

public interface DeclarationRepository {
    Optional<Declaration> findByPatientId(long id);
}
