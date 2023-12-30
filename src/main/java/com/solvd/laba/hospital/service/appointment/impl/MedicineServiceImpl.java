package com.solvd.laba.hospital.service.appointment.impl;

import com.solvd.laba.hospital.dao.repository.appointment.MedicineRepository;
import com.solvd.laba.hospital.dao.repository.appointment.impl.MedicineRepositoryJdbcImpl;
import com.solvd.laba.hospital.model.appointment.Medicine;
import com.solvd.laba.hospital.model.exceptions.IncorrectMedicineException;
import com.solvd.laba.hospital.service.appointment.MedicineService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MedicineServiceImpl implements MedicineService {

    private final static Logger LOGGER = LogManager.getLogger(MedicineServiceImpl.class);
    private final MedicineRepository medicineRepository = new MedicineRepositoryJdbcImpl();

    @Override
    public Medicine create(Medicine medicine) {
        try {
            validate(medicine);
            medicineRepository.create(medicine);
        } catch (IncorrectMedicineException e) {
            LOGGER.error(e);
        }
        return medicine;
    }

    private void validate(Medicine medicine) throws IncorrectMedicineException {
        if (medicine.getName() == null) {
            throw new IncorrectMedicineException("Name cannot be null");
        }
        if (medicine.getPrice() < 0) {
            throw new IncorrectMedicineException("Price cannot be negative");
        }
    }
}
