package com.solvd.laba.hospital.dao.repository;

import com.solvd.laba.hospital.model.info.Analysis;

import java.util.List;

public interface AnalysisRepository {
    List<Analysis> findAllByPatientId(long id);
}
