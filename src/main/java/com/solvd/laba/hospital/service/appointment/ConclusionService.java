package com.solvd.laba.hospital.service.appointment;

import com.solvd.laba.hospital.model.appointment.Conclusion;

public interface ConclusionService {
    Conclusion create(Conclusion conclusion, long appointmentId);
}
