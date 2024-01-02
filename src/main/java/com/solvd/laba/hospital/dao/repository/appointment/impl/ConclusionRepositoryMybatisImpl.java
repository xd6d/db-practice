package com.solvd.laba.hospital.dao.repository.appointment.impl;

import com.solvd.laba.hospital.dao.Config;
import com.solvd.laba.hospital.dao.repository.appointment.ConclusionMedicineMapper;
import com.solvd.laba.hospital.dao.repository.appointment.ConclusionRepository;
import com.solvd.laba.hospital.model.appointment.Conclusion;
import org.apache.ibatis.session.SqlSession;

public class ConclusionRepositoryMybatisImpl implements ConclusionRepository {
    @Override
    public void create(Conclusion conclusion, long appointmentId) {
        try (SqlSession session = Config.getSessionFactory().openSession(true)) {
            ConclusionRepository conclusionRepository = session.getMapper(ConclusionRepository.class);
            conclusionRepository.create(conclusion, appointmentId);
            session.getMapper(ConclusionMedicineMapper.class).create(conclusion.getMedicines(), conclusion.getId());
        }
    }

    @Override
    public boolean existsByAppointmentId(long appointmentId) {
        try (SqlSession session = Config.getSessionFactory().openSession(true)) {
            ConclusionRepository conclusionRepository = session.getMapper(ConclusionRepository.class);
            return conclusionRepository.existsByAppointmentId(appointmentId);
        }
    }
}
