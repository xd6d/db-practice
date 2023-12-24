package com.solvd.laba.hospital.service.appointment;

import com.solvd.laba.hospital.model.appointment.Appointment;

public interface AppointmentService {
    Appointment add(Appointment appointment);

    boolean existsById(long id);
}
