package com.solvd.laba.hospital.service.impl;

import com.solvd.laba.hospital.dao.repository.DeclarationRepository;
import com.solvd.laba.hospital.dao.repository.impl.DeclarationRepositoryImpl;
import com.solvd.laba.hospital.model.info.Declaration;
import com.solvd.laba.hospital.service.DeclarationService;

public class DeclarationServiceImpl implements DeclarationService {
    private final DeclarationRepository declarationRepository;

    public DeclarationServiceImpl() {
        this.declarationRepository = new DeclarationRepositoryImpl();
    }

    @Override
    public Declaration getByPatientId(long id) {
        return declarationRepository.findByPatientId(id).orElse(null);
    }
}
