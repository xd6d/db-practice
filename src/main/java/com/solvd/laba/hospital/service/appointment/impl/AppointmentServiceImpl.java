package com.solvd.laba.hospital.service.appointment.impl;

import com.solvd.laba.hospital.dao.repository.appointment.AppointmentRepository;
import com.solvd.laba.hospital.dao.repository.appointment.impl.AppointmentRepositoryImpl;
import com.solvd.laba.hospital.model.appointment.Appointment;
import com.solvd.laba.hospital.model.exceptions.IncorrectAppointmentException;
import com.solvd.laba.hospital.service.appointment.AppointmentService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AppointmentServiceImpl implements AppointmentService {
    private final static Logger LOGGER = LogManager.getLogger(AppointmentServiceImpl.class);
    private final AppointmentRepository appointmentRepository = new AppointmentRepositoryImpl();

    @Override
    public Appointment add(Appointment appointment) {
        try {
            validate(appointment);
            appointmentRepository.create(appointment);
        } catch (IncorrectAppointmentException e) {
            LOGGER.error(e);
        }
        return appointment;
    }

    @Override
    public boolean existsById(long id) {
        return appointmentRepository.existsById(id);
    }

    private void validate(Appointment appointment) throws IncorrectAppointmentException {
        if (appointment.getTime() == null) {
            throw new IncorrectAppointmentException("Time cannot be null");
        }
        if (appointment.getDoctor() == null) {
            throw new IncorrectAppointmentException("Doctor cannot be null");
        }
        if (appointment.getPatient() == null) {
            throw new IncorrectAppointmentException("Patient cannot be null");
        }
    }
}
