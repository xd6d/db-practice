package com.solvd.laba.hospital.model.appointment;

import com.solvd.laba.hospital.model.person.EmployeePerson;
import com.solvd.laba.hospital.model.person.PatientPerson;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

@Data
@EqualsAndHashCode(of = "id")
public class Appointment {
    private long id;
    private Date time;
    private PatientPerson patient;
    private EmployeePerson doctor;
    private Conclusion conclusion;
    private List<Service> services;
}
