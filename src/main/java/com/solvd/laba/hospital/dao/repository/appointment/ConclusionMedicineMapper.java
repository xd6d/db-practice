package com.solvd.laba.hospital.dao.repository.appointment;

import com.solvd.laba.hospital.model.appointment.Medicine;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ConclusionMedicineMapper {
    void create(@Param("medicines") List<Medicine> medicines, @Param("conclusionId") long conclusionId);
}
