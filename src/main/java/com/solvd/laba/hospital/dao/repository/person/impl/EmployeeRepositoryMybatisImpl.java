package com.solvd.laba.hospital.dao.repository.person.impl;

import com.solvd.laba.hospital.dao.Config;
import com.solvd.laba.hospital.dao.repository.person.EmployeeRepository;
import com.solvd.laba.hospital.model.person.EmployeePerson;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Optional;

public class EmployeeRepositoryMybatisImpl implements EmployeeRepository {

    @Override
    public void create(EmployeePerson employee) {
        try (SqlSession session = Config.getSessionFactory().openSession(true)) {
            EmployeeRepository employeeRepository = session.getMapper(EmployeeRepository.class);
            employeeRepository.create(employee);
        }
    }

    @Override
    public List<EmployeePerson> findAll() {
        try (SqlSession session = Config.getSessionFactory().openSession(true)) {
            EmployeeRepository employeeRepository = session.getMapper(EmployeeRepository.class);
            return employeeRepository.findAll();
        }
    }

    @Override
    public Optional<EmployeePerson> findById(long id) {
        return Optional.empty();
    }

    @Override
    public void update(EmployeePerson entity) {

    }

    @Override
    public void deleteById(long id) {

    }
}
