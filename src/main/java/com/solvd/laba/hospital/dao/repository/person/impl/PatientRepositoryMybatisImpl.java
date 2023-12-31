package com.solvd.laba.hospital.dao.repository.person.impl;

import com.solvd.laba.hospital.dao.Config;
import com.solvd.laba.hospital.dao.repository.person.PatientRepository;
import com.solvd.laba.hospital.model.person.PatientPerson;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Optional;

public class PatientRepositoryMybatisImpl implements PatientRepository {
    @Override
    public void create(PatientPerson patient) {
        try (SqlSession session = Config.getSessionFactory().openSession(true)) {
            PatientRepository patientRepository = session.getMapper(PatientRepository.class);
            patientRepository.create(patient);
        }
    }

    @Override
    public List<PatientPerson> findAll() {
        try (SqlSession session = Config.getSessionFactory().openSession(true)) {
            PatientRepository patientRepository = session.getMapper(PatientRepository.class);
            return patientRepository.findAll();
        }
    }

    @Override
    public Optional<PatientPerson> findById(long id) {
        try (SqlSession session = Config.getSessionFactory().openSession(true)) {
            PatientRepository patientRepository = session.getMapper(PatientRepository.class);
            return patientRepository.findById(id);
        }
    }

    @Override
    public void update(PatientPerson patient) {
        try (SqlSession session = Config.getSessionFactory().openSession(true)) {
            PatientRepository patientRepository = session.getMapper(PatientRepository.class);
            patientRepository.update(patient);
        }
    }

    @Override
    public void deleteById(long id) {
        try (SqlSession session = Config.getSessionFactory().openSession(true)) {
            PatientRepository patientRepository = session.getMapper(PatientRepository.class);
            patientRepository.deleteById(id);
        }
    }
}
