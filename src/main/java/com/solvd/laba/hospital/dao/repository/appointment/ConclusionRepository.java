package com.solvd.laba.hospital.dao.repository.appointment;

import com.solvd.laba.hospital.model.appointment.Conclusion;
import org.apache.ibatis.annotations.Param;

public interface ConclusionRepository {
    void create(@Param("conclusion") Conclusion conclusion, @Param("appointmentId") long appointmentId);

    boolean existsByAppointmentId(long appointmentId);
}
