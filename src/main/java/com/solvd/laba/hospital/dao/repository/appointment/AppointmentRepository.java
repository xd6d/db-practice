package com.solvd.laba.hospital.dao.repository.appointment;

import com.solvd.laba.hospital.model.appointment.Appointment;

public interface AppointmentRepository {
    Appointment create(Appointment appointment);

    boolean existsById(long id);
}
