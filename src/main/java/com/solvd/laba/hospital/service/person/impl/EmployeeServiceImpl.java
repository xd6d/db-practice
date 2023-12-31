package com.solvd.laba.hospital.service.person.impl;

import com.solvd.laba.hospital.dao.repository.person.EmployeeRepository;
import com.solvd.laba.hospital.dao.repository.person.impl.EmployeeRepositoryMybatisImpl;
import com.solvd.laba.hospital.model.exceptions.IncorrectEmployeeException;
import com.solvd.laba.hospital.model.exceptions.IncorrectPersonException;
import com.solvd.laba.hospital.model.person.EmployeePerson;
import com.solvd.laba.hospital.service.person.EmployeeService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class EmployeeServiceImpl extends PersonService implements EmployeeService {
    private static final Logger LOGGER = LogManager.getLogger(EmployeeServiceImpl.class);

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl() {
        this.employeeRepository = new EmployeeRepositoryMybatisImpl();
//        this.employeeRepository = new EmployeeRepositoryJdbcImpl();
    }

    @Override
    public EmployeePerson add(EmployeePerson employee) {
        try {
            validateForNull(employee);
            validateUniqueness(employee, getAll(), false);
            employeeRepository.create(employee);
        } catch (IncorrectPersonException e) {
            LOGGER.error(e);
        }
        return employee;
    }

    @Override
    public List<EmployeePerson> getAll() {
        return employeeRepository.findAll();
    }

    private void validateForNull(EmployeePerson employee) throws IncorrectPersonException {
        super.validateForNull(employee);
        if (employee.getPosition() == null) {
            throw new IncorrectEmployeeException("Position cannot be null");
        }
    }
}
