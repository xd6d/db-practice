package com.solvd.laba.hospital.dao.repository.appointment;

import com.solvd.laba.hospital.model.appointment.Service;

public interface MedicalRepository {
    void create(Service medicalService);
}
