package com.solvd.laba.hospital.dao.repository.appointment.impl;

import com.solvd.laba.hospital.dao.Config;
import com.solvd.laba.hospital.dao.repository.appointment.MedicineRepository;
import com.solvd.laba.hospital.model.appointment.Medicine;
import org.apache.ibatis.session.SqlSession;

public class MedicineRepositoryMybatisImpl implements MedicineRepository {
    @Override
    public void create(Medicine medicine) {
        try (SqlSession session = Config.getSessionFactory().openSession(true)) {
            MedicineRepository medicineRepository = session.getMapper(MedicineRepository.class);
            medicineRepository.create(medicine);
        }
    }
}
