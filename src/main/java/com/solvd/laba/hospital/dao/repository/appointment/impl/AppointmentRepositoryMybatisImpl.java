package com.solvd.laba.hospital.dao.repository.appointment.impl;

import com.solvd.laba.hospital.dao.Config;
import com.solvd.laba.hospital.dao.repository.appointment.AppointmentRepository;
import com.solvd.laba.hospital.dao.repository.appointment.AppointmentServiceMapper;
import com.solvd.laba.hospital.model.appointment.Appointment;
import org.apache.ibatis.session.SqlSession;

public class AppointmentRepositoryMybatisImpl implements AppointmentRepository {
    @Override
    public void create(Appointment appointment) {
        try (SqlSession session = Config.getSessionFactory().openSession(true)) {
            AppointmentRepository appointmentRepository = session.getMapper(AppointmentRepository.class);
            appointmentRepository.create(appointment);
            session.getMapper(AppointmentServiceMapper.class).create(appointment.getServices(), appointment.getId());
        }
    }

    @Override
    public boolean existsById(long id) {
        try (SqlSession session = Config.getSessionFactory().openSession(true)) {
            AppointmentRepository appointmentRepository = session.getMapper(AppointmentRepository.class);
            return appointmentRepository.existsById(id);
        }
    }
}
