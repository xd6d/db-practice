package com.solvd.laba.hospital.service.appointment.impl;

import com.solvd.laba.hospital.dao.repository.appointment.MedicalRepository;
import com.solvd.laba.hospital.dao.repository.appointment.impl.MedicalRepositoryMybatisImpl;
import com.solvd.laba.hospital.model.appointment.Service;
import com.solvd.laba.hospital.model.exceptions.IncorrectServiceException;
import com.solvd.laba.hospital.service.appointment.MedicalService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MedicalServiceImpl implements MedicalService {
    private final static Logger LOGGER = LogManager.getLogger(MedicalServiceImpl.class);
    private final MedicalRepository medicalRepository = new MedicalRepositoryMybatisImpl();
//    private final MedicalRepository medicalRepository = new MedicalRepositoryJdbcImpl();

    @Override
    public Service create(Service service) {
        try {
            validate(service);
            medicalRepository.create(service);
        } catch (IncorrectServiceException e) {
            LOGGER.error(e);
        }
        return service;
    }

    private void validate(Service service) throws IncorrectServiceException {
        if (service.getName() == null) {
            throw new IncorrectServiceException("Name cannot be null");
        }
        if (service.getPrice() < 0) {
            throw new IncorrectServiceException("Price cannot be negative");
        }
    }
}
