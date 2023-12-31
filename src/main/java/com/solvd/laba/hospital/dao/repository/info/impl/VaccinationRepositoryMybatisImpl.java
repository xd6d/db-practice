package com.solvd.laba.hospital.dao.repository.info.impl;

import com.solvd.laba.hospital.dao.Config;
import com.solvd.laba.hospital.dao.repository.info.VaccinationRepository;
import com.solvd.laba.hospital.model.info.Vaccination;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class VaccinationRepositoryMybatisImpl implements VaccinationRepository {
    @Override
    public List<Vaccination> findAllByPatientId(long id) {
        try (SqlSession session = Config.getSessionFactory().openSession(true)) {
            VaccinationRepository vaccinationRepository = session.getMapper(VaccinationRepository.class);
            return vaccinationRepository.findAllByPatientId(id);
        }
    }

    @Override
    public void create(Vaccination vaccination, long patientId) {
        try (SqlSession session = Config.getSessionFactory().openSession(true)) {
            VaccinationRepository vaccinationRepository = session.getMapper(VaccinationRepository.class);
            vaccinationRepository.create(vaccination, patientId);
        }
    }
}
