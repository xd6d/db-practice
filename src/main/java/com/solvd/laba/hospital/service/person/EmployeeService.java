package com.solvd.laba.hospital.service.person;

import com.solvd.laba.hospital.model.person.EmployeePerson;

import java.util.List;

public interface EmployeeService {
    EmployeePerson add(EmployeePerson employee);

    List<EmployeePerson> getAll();
}
