package com.solvd.laba.hospital.dao.repository.appointment;

import com.solvd.laba.hospital.model.appointment.Conclusion;

public interface ConclusionRepository {
    Conclusion create(Conclusion conclusion, long appointmentId);
}
