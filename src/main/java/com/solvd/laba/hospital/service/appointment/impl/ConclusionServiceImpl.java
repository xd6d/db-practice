package com.solvd.laba.hospital.service.appointment.impl;

import com.solvd.laba.hospital.dao.repository.appointment.ConclusionRepository;
import com.solvd.laba.hospital.dao.repository.appointment.impl.ConclusionRepositoryJdbcImpl;
import com.solvd.laba.hospital.model.appointment.Conclusion;
import com.solvd.laba.hospital.model.exceptions.IncorrectAppointmentException;
import com.solvd.laba.hospital.service.appointment.AppointmentService;
import com.solvd.laba.hospital.service.appointment.ConclusionService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConclusionServiceImpl implements ConclusionService {
    private final static Logger LOGGER = LogManager.getLogger(ConclusionServiceImpl.class);
    private final ConclusionRepository conclusionRepository = new ConclusionRepositoryJdbcImpl();
    private final AppointmentService appointmentService = new AppointmentServiceImpl();

    @Override
    public Conclusion create(Conclusion conclusion, long appointmentId) {
        try {
            validate(appointmentId);
            conclusionRepository.create(conclusion, appointmentId);
        } catch (IncorrectAppointmentException e) {
            LOGGER.error(e);
        }
        return conclusion;
    }

    private void validate(long appointmentId) throws IncorrectAppointmentException {
        if (!appointmentService.existsById(appointmentId)) {
            throw new IncorrectAppointmentException("Appointment with given id does not exist");
        }
    }
}
