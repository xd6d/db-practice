package com.solvd.laba.hospital.service.info.impl;

import com.solvd.laba.hospital.dao.repository.info.VaccinationRepository;
import com.solvd.laba.hospital.dao.repository.info.impl.VaccinationRepositoryMybatisImpl;
import com.solvd.laba.hospital.model.exceptions.IncorrectInfoException;
import com.solvd.laba.hospital.model.exceptions.IncorrectVaccinationException;
import com.solvd.laba.hospital.model.info.Vaccination;
import com.solvd.laba.hospital.service.info.VaccinationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class VaccinationServiceImpl extends InfoService implements VaccinationService {
    private static final Logger LOGGER = LogManager.getLogger(VaccinationServiceImpl.class);
    private final VaccinationRepository vaccinationRepository = new VaccinationRepositoryMybatisImpl();
//    private final VaccinationRepository vaccinationRepository = new VaccinationRepositoryJdbcImpl();

    @Override
    public List<Vaccination> getAllByPatientId(long id) {
        return vaccinationRepository.findAllByPatientId(id);
    }

    @Override
    public Vaccination add(Vaccination vaccination, long patientId) {
        try {
            validate(vaccination);
            validate(patientId);
            vaccinationRepository.create(vaccination, patientId);
        } catch (IncorrectInfoException e) {
            LOGGER.error(e);
        }
        return vaccination;
    }

    private void validate(Vaccination vaccination) throws IncorrectVaccinationException {
        if (vaccination.getName() == null) {
            throw new IncorrectVaccinationException("Name cannot be null");
        }
        if (vaccination.getDate() == null) {
            throw new IncorrectVaccinationException("Date cannot be null");
        }
        if (vaccination.getExpires() == null) {
            throw new IncorrectVaccinationException("Expiry date cannot be null");
        }
    }
}
