package com.solvd.laba.hospital.service.impl;

import com.solvd.laba.hospital.dao.repository.AnalysisRepository;
import com.solvd.laba.hospital.dao.repository.impl.AnalysisRepositoryImpl;
import com.solvd.laba.hospital.model.exceptions.IncorrectAnalysisException;
import com.solvd.laba.hospital.model.info.Analysis;
import com.solvd.laba.hospital.service.AnalysisService;
import com.solvd.laba.hospital.service.PatientService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class AnalysisServiceImpl implements AnalysisService {
    private static final Logger LOGGER = LogManager.getLogger(AnalysisServiceImpl.class);
    private final AnalysisRepository analysisRepository;
    private final PatientService patientService;

    public AnalysisServiceImpl() {
        this.analysisRepository = new AnalysisRepositoryImpl();
        this.patientService = new PatientServiceImpl();
    }

    protected AnalysisServiceImpl(PatientService patientService) {
        this.analysisRepository = new AnalysisRepositoryImpl();
        this.patientService = patientService;
    }

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
        } catch (IncorrectAnalysisException e) {
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
            throw new IncorrectAnalysisException("Name can not be null");
        }
        if (analysis.getValue() == null) {
            throw new IncorrectAnalysisException("Value can not be null");
        }
        if (analysis.getUnit() == null) {
            throw new IncorrectAnalysisException("Unit can not be null");
        }
        if (analysis.getHealthyValue() == null) {
            throw new IncorrectAnalysisException("Healthy value can not be null");
        }
    }

    private void validate(long patientId) throws IncorrectAnalysisException {
        if (patientService.getById(patientId).isEmpty()) {
            throw new IncorrectAnalysisException("Patient with given id does not exist");
        }
    }
}
