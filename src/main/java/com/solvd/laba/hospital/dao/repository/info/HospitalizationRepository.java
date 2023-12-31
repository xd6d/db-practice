package com.solvd.laba.hospital.dao.repository.info;

import com.solvd.laba.hospital.model.info.Hospitalization;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HospitalizationRepository {
    List<Hospitalization> findAllByPatientId(long id);

    void create(@Param("hospitalization") Hospitalization hospitalization, @Param("patientId") long patientId);
}
