package com.solvd.laba.hospital.dao.repository.info.impl;

import com.solvd.laba.hospital.dao.Config;
import com.solvd.laba.hospital.dao.repository.info.AllergyRepository;
import com.solvd.laba.hospital.model.info.Allergy;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class AllergyRepositoryMybatisImpl implements AllergyRepository {

    @Override
    public List<Allergy> findAllByPatientId(long id) {
        try (SqlSession session = Config.getSessionFactory().openSession(true)) {
            AllergyRepository allergyRepository = session.getMapper(AllergyRepository.class);
            return allergyRepository.findAllByPatientId(id);
        }
    }

    @Override
    public void create(Allergy allergy, long patientId) {
        try (SqlSession session = Config.getSessionFactory().openSession(true)) {
            AllergyRepository allergyRepository = session.getMapper(AllergyRepository.class);
            allergyRepository.create(allergy, patientId);
        }
    }
}
