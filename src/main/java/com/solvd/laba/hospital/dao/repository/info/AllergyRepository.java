package com.solvd.laba.hospital.dao.repository.info;

import com.solvd.laba.hospital.model.info.Allergy;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AllergyRepository {
    List<Allergy> findAllByPatientId(long id);

    void create(@Param("allergy") Allergy allergy, @Param("patientId") long patientId);
}
