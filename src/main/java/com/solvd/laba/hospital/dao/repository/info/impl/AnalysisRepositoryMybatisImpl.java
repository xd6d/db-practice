package com.solvd.laba.hospital.dao.repository.info.impl;

import com.solvd.laba.hospital.dao.Config;
import com.solvd.laba.hospital.dao.repository.info.AnalysisRepository;
import com.solvd.laba.hospital.model.info.Analysis;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class AnalysisRepositoryMybatisImpl implements AnalysisRepository {
    @Override
    public List<Analysis> findAllByPatientId(long id) {
        try (SqlSession session = Config.getSessionFactory().openSession(true)) {
            AnalysisRepository analysisRepository = session.getMapper(AnalysisRepository.class);
            return analysisRepository.findAllByPatientId(id);
        }
    }

    @Override
    public void create(Analysis analysis, long patientId) {
        try (SqlSession session = Config.getSessionFactory().openSession(true)) {
            AnalysisRepository analysisRepository = session.getMapper(AnalysisRepository.class);
            analysisRepository.create(analysis, patientId);
        }
    }

    @Override
    public void update(Analysis analysis) {
        try (SqlSession session = Config.getSessionFactory().openSession(true)) {
            AnalysisRepository analysisRepository = session.getMapper(AnalysisRepository.class);
            analysisRepository.update(analysis);
        }
    }

    @Override
    public void deleteById(long id) {
        try (SqlSession session = Config.getSessionFactory().openSession(true)) {
            AnalysisRepository analysisRepository = session.getMapper(AnalysisRepository.class);
            analysisRepository.deleteById(id);
        }
    }
}
