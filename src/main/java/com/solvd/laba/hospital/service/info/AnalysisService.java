package com.solvd.laba.hospital.service.info;

import com.solvd.laba.hospital.model.info.Analysis;

import java.util.List;

public interface AnalysisService {
    List<Analysis> getAllByPatientId(long id);

    Analysis add(Analysis analysis, long patientId);

    void update(Analysis analysis);

    void deleteById(long id);
}
