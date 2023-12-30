package com.solvd.laba.hospital.dao.repository.info;

import com.solvd.laba.hospital.model.info.Declaration;
import org.apache.ibatis.annotations.Param;

import java.util.Optional;

public interface DeclarationRepository {
    Optional<Declaration> findByPatientId(long id);

    void create(@Param("declaration") Declaration declaration, @Param("patientId") long patientId);

    void update(Declaration declaration);

    void deleteById(long id);
}
