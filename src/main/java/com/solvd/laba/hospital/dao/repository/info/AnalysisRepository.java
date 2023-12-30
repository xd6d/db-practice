package com.solvd.laba.hospital.dao.repository.info;

import com.solvd.laba.hospital.model.info.Analysis;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AnalysisRepository {
    List<Analysis> findAllByPatientId(long id);

    void create(@Param("analysis") Analysis analysis, @Param("patientId") long patientId);

    void update(Analysis analysis);

    void deleteById(long id);
}
