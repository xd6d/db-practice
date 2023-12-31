package com.solvd.laba.hospital.dao.repository.info.impl;

import com.solvd.laba.hospital.dao.Config;
import com.solvd.laba.hospital.dao.repository.info.DeclarationRepository;
import com.solvd.laba.hospital.model.info.Declaration;
import org.apache.ibatis.session.SqlSession;

import java.util.Optional;

public class DeclarationRepositoryMybatisImpl implements DeclarationRepository {

    @Override
    public Optional<Declaration> findByPatientId(long id) {
        try (SqlSession session = Config.getSessionFactory().openSession(true)) {
            DeclarationRepository declarationRepository = session.getMapper(DeclarationRepository.class);
            return declarationRepository.findByPatientId(id);
        }
    }

    @Override
    public void create(Declaration declaration, long patientId) {
        try (SqlSession session = Config.getSessionFactory().openSession(true)) {
            DeclarationRepository declarationRepository = session.getMapper(DeclarationRepository.class);
            declarationRepository.create(declaration, patientId);
        }
    }

    @Override
    public void update(Declaration declaration) {
        try (SqlSession session = Config.getSessionFactory().openSession(true)) {
            DeclarationRepository declarationRepository = session.getMapper(DeclarationRepository.class);
            declarationRepository.update(declaration);
        }
    }

    @Override
    public void deleteById(long id) {
        try (SqlSession session = Config.getSessionFactory().openSession(true)) {
            DeclarationRepository declarationRepository = session.getMapper(DeclarationRepository.class);
            declarationRepository.deleteById(id);
        }
    }
}
