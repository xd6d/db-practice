package com.solvd.laba.hospital.service.info.impl;

import com.solvd.laba.hospital.dao.repository.info.HospitalizationRepository;
import com.solvd.laba.hospital.dao.repository.info.impl.HospitalizationRepositoryImpl;
import com.solvd.laba.hospital.model.exceptions.IncorrectHospitalizationException;
import com.solvd.laba.hospital.model.exceptions.IncorrectInfoException;
import com.solvd.laba.hospital.model.info.Hospitalization;
import com.solvd.laba.hospital.service.info.HospitalizationService;
import com.solvd.laba.hospital.service.person.PatientService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class HospitalizationServiceImpl extends InfoService implements HospitalizationService {
    private static final Logger LOGGER = LogManager.getLogger(HospitalizationServiceImpl.class);
    private final HospitalizationRepository hospitalizationRepository = new HospitalizationRepositoryImpl();

    public HospitalizationServiceImpl(PatientService patientService) {
        super(patientService);
    }

    @Override
    public List<Hospitalization> getAllByPatientId(long id) {
        return hospitalizationRepository.findAllByPatientId(id);
    }

    @Override
    public Hospitalization add(Hospitalization hospitalization, long patientId) {
        try {
            validate(hospitalization);
            validate(patientId);
            hospitalizationRepository.create(hospitalization, patientId);
        } catch (IncorrectInfoException e) {
            LOGGER.error(e);
        }
        return hospitalization;
    }

    private void validate(Hospitalization hospitalization) throws IncorrectHospitalizationException {
        if (hospitalization.getPlace() == null) {
            throw new IncorrectHospitalizationException("Place cannot be null");
        }
        if (hospitalization.getDate() == null) {
            throw new IncorrectHospitalizationException("Date cannot be null");
        }
    }
}
