package com.solvd.laba.hospital.service;

import com.solvd.laba.hospital.model.info.Analysis;

import java.util.List;

public interface AnalysisService {
    List<Analysis> getAllByPatientId(long id);
}
