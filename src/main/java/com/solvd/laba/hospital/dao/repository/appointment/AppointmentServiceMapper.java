package com.solvd.laba.hospital.dao.repository.appointment;

import com.solvd.laba.hospital.model.appointment.Service;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AppointmentServiceMapper {
    void create(@Param("services") List<Service> services, @Param("appointmentId") long appointmentId);
}
