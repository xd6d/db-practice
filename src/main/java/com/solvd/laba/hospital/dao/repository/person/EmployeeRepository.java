package com.solvd.laba.hospital.dao.repository.person;

import com.solvd.laba.hospital.model.person.EmployeePerson;

import java.util.List;

public interface EmployeeRepository {
    void create(EmployeePerson employee);

    List<EmployeePerson> findAll();
}
