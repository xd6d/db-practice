package com.solvd.laba.hospital.dao.repository.info.impl;

import com.solvd.laba.hospital.dao.Config;
import com.solvd.laba.hospital.dao.repository.info.HospitalizationRepository;
import com.solvd.laba.hospital.model.info.Hospitalization;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class HospitalizationRepositoryMybatisImpl implements HospitalizationRepository {

    @Override
    public List<Hospitalization> findAllByPatientId(long id) {
        try (SqlSession session = Config.getSessionFactory().openSession(true)) {
            HospitalizationRepository hospitalizationRepository = session.getMapper(HospitalizationRepository.class);
            return hospitalizationRepository.findAllByPatientId(id);
        }
    }

    @Override
    public void create(Hospitalization hospitalization, long patientId) {
        try (SqlSession session = Config.getSessionFactory().openSession(true)) {
            HospitalizationRepository hospitalizationRepository = session.getMapper(HospitalizationRepository.class);
            hospitalizationRepository.create(hospitalization, patientId);
        }
    }
}
