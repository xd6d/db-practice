package com.solvd.laba.hospital.dao.repository.appointment.impl;

import com.solvd.laba.hospital.dao.Config;
import com.solvd.laba.hospital.dao.repository.appointment.MedicalRepository;
import com.solvd.laba.hospital.model.appointment.Service;
import org.apache.ibatis.session.SqlSession;

public class MedicalRepositoryMybatisImpl implements MedicalRepository {

    @Override
    public void create(Service medicalService) {
        try (SqlSession session = Config.getSessionFactory().openSession(true)) {
            MedicalRepository medicalRepository = session.getMapper(MedicalRepository.class);
            medicalRepository.create(medicalService);
        }
    }
}
