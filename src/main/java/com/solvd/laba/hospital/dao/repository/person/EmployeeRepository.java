package com.solvd.laba.hospital.dao.repository.person;

import com.solvd.laba.hospital.model.person.EmployeePerson;

import java.util.List;

public interface EmployeeRepository {
    EmployeePerson create(EmployeePerson employee);

    List<EmployeePerson> findAll();
}
