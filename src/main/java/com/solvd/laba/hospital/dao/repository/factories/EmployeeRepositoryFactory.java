package com.solvd.laba.hospital.dao.repository.factories;

import com.solvd.laba.hospital.dao.repository.Repository;
import com.solvd.laba.hospital.dao.repository.person.EmployeeRepository;
import com.solvd.laba.hospital.dao.repository.person.impl.EmployeeRepositoryJdbcImpl;
import com.solvd.laba.hospital.dao.repository.person.impl.EmployeeRepositoryMybatisImpl;
import com.solvd.laba.hospital.model.exceptions.NoSuchTypeException;
import com.solvd.laba.hospital.model.person.EmployeePerson;

import java.util.Locale;

public class EmployeeRepositoryFactory implements RepositoryFactory {
    @Override
    public Repository<EmployeePerson> createRepository(String type) {
        EmployeeRepository result;
        switch (type.toLowerCase(Locale.ROOT)) {
            case "jdbc":
                result = new EmployeeRepositoryJdbcImpl();
                break;
            case "mybatis":
                result = new EmployeeRepositoryMybatisImpl();
                break;
            default:
                throw new NoSuchTypeException("Such repository type does not exist: %s".formatted(type));
        }
        return result;
    }
}
