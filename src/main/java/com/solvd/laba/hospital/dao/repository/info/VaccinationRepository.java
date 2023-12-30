package com.solvd.laba.hospital.dao.repository.info;

import com.solvd.laba.hospital.model.info.Vaccination;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface VaccinationRepository {
    List<Vaccination> findAllByPatientId(long id);

    void create(@Param("vaccination") Vaccination vaccination, @Param("patientId") long patientId);
}
