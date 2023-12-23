package com.solvd.laba.hospital.service.impl;

import com.solvd.laba.hospital.dao.repository.AnalysisRepository;
import com.solvd.laba.hospital.dao.repository.impl.AnalysisRepositoryImpl;
import com.solvd.laba.hospital.model.info.Analysis;
import com.solvd.laba.hospital.service.AnalysisService;

import java.util.List;

public class AnalysisServiceImpl implements AnalysisService {
    private final AnalysisRepository analysisRepository;

    public AnalysisServiceImpl() {
        this.analysisRepository = new AnalysisRepositoryImpl();
    }

    @Override
    public List<Analysis> getAllByPatientId(long id) {
        return analysisRepository.findAllByPatientId(id);
    }
}
