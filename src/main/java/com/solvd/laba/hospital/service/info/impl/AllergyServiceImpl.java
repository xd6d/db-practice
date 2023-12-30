package com.solvd.laba.hospital.service.info.impl;

import com.solvd.laba.hospital.dao.repository.info.AllergyRepository;
import com.solvd.laba.hospital.dao.repository.info.impl.AllergyRepositoryJdbcImpl;
import com.solvd.laba.hospital.model.exceptions.IncorrectAllergyException;
import com.solvd.laba.hospital.model.exceptions.IncorrectInfoException;
import com.solvd.laba.hospital.model.info.Allergy;
import com.solvd.laba.hospital.service.info.AllergyService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class AllergyServiceImpl extends InfoService implements AllergyService {
    private static final Logger LOGGER = LogManager.getLogger(AllergyServiceImpl.class);
    private final AllergyRepository allergyRepository = new AllergyRepositoryJdbcImpl();

    @Override
    public List<Allergy> getAllByPatientId(long id) {
        return allergyRepository.findAllByPatientId(id);
    }

    @Override
    public Allergy add(Allergy allergy, long patientId) {
        try {
            validate(allergy);
            validate(patientId);
            allergyRepository.create(allergy, patientId);
        } catch (IncorrectInfoException e) {
            LOGGER.error(e);
        }
        return allergy;
    }

    private void validate(Allergy allergy) throws IncorrectAllergyException {
        if (allergy.getName() == null) {
            throw new IncorrectAllergyException("Name cannot be null");
        }
    }
}
