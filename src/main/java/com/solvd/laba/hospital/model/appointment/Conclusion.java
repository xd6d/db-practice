package com.solvd.laba.hospital.model.appointment;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(of = "id")
public class Conclusion {
    private long id;
    private String complaint;
    private String medicalHistory;
    private String observation;
    private String diagnosis;
    private String recommendation;
    private List<Medicine> medicines;
}

