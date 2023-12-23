package com.solvd.laba.hospital.service.info.impl;

import com.solvd.laba.hospital.dao.repository.info.DeclarationRepository;
import com.solvd.laba.hospital.dao.repository.info.impl.DeclarationRepositoryImpl;
import com.solvd.laba.hospital.model.exceptions.IncorrectDeclarationException;
import com.solvd.laba.hospital.model.info.Declaration;
import com.solvd.laba.hospital.service.info.DeclarationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Date;

public class DeclarationServiceImpl implements DeclarationService {
    private static final Logger LOGGER = LogManager.getLogger(DeclarationServiceImpl.class);
    private final DeclarationRepository declarationRepository;

    public DeclarationServiceImpl() {
        this.declarationRepository = new DeclarationRepositoryImpl();
    }

    @Override
    public Declaration getByPatientId(long id) {
        return declarationRepository.findByPatientId(id).orElse(null);
    }

    @Override
    public Declaration add(Declaration declaration) {
        try {
            validate(declaration);
            declarationRepository.create(declaration);
        } catch (IncorrectDeclarationException e) {
            LOGGER.error(e);
        }
        return declaration;
    }

    @Override
    public void update(Declaration declaration) {
        try {
            validateExpires(declaration);
            declarationRepository.update(declaration);
        } catch (IncorrectDeclarationException e) {
            LOGGER.error(e);
        }
    }

    @Override
    public void deleteById(long id) {
        declarationRepository.deleteById(id);
    }

    private void validate(Declaration declaration) throws IncorrectDeclarationException {
        if (declaration.getDoctor() == null) {
            throw new IncorrectDeclarationException("Doctor can not be null");
        }
        if (!declaration.getDoctor().getPosition().isDoctor()) {
            throw new IncorrectDeclarationException("Set doctor");
        }
        validateExpires(declaration);
    }

    private void validateExpires(Declaration declaration) throws IncorrectDeclarationException {
        if (declaration.getExpires() == null) {
            throw new IncorrectDeclarationException("Expires date can not be null");
        }
        if (declaration.getExpires().compareTo(new Date()) < 0) {
            throw new IncorrectDeclarationException("Expires can not be in past");
        }
    }
}
