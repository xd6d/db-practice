package com.solvd.laba.hospital.service.info.impl;

import com.solvd.laba.hospital.dao.repository.info.AnalysisRepository;
import com.solvd.laba.hospital.dao.repository.info.impl.AnalysisRepositoryJdbcImpl;
import com.solvd.laba.hospital.model.exceptions.IncorrectAnalysisException;
import com.solvd.laba.hospital.model.exceptions.IncorrectInfoException;
import com.solvd.laba.hospital.model.info.Analysis;
import com.solvd.laba.hospital.service.info.AnalysisService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class AnalysisServiceImpl extends InfoService implements AnalysisService {
    private static final Logger LOGGER = LogManager.getLogger(AnalysisServiceImpl.class);
    private final AnalysisRepository analysisRepository = new AnalysisRepositoryJdbcImpl();

    @Override
    public List<Analysis> getAllByPatientId(long id) {
        return analysisRepository.findAllByPatientId(id);
    }

    @Override
    public Analysis add(Analysis analysis, long patientId) {
        try {
            validate(analysis);
            validate(patientId);
            analysisRepository.create(analysis, patientId);
        } catch (IncorrectInfoException e) {
            LOGGER.error(e);
        }
        return analysis;
    }

    @Override
    public void update(Analysis analysis) {
        try {
            validate(analysis);
            analysisRepository.update(analysis);
        } catch (IncorrectAnalysisException e) {
            LOGGER.error(e);
        }
    }

    @Override
    public void deleteById(long id) {
        analysisRepository.deleteById(id);
    }

    private void validate(Analysis analysis) throws IncorrectAnalysisException {
        if (analysis.getName() == null) {
            throw new IncorrectAnalysisException("Name cannot be null");
        }
        if (analysis.getValue() == null) {
            throw new IncorrectAnalysisException("Value cannot be null");
        }
        if (analysis.getUnit() == null) {
            throw new IncorrectAnalysisException("Unit cannot be null");
        }
        if (analysis.getHealthyValue() == null) {
            throw new IncorrectAnalysisException("Healthy value cannot be null");
        }
    }
}
