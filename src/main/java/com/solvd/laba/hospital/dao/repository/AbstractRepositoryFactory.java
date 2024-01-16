package com.solvd.laba.hospital.dao.repository;

import com.solvd.laba.hospital.dao.repository.factories.EmployeeRepositoryFactory;
import com.solvd.laba.hospital.dao.repository.factories.PatientRepositoryFactory;
import com.solvd.laba.hospital.dao.repository.factories.RepositoryFactory;
import com.solvd.laba.hospital.model.exceptions.NoSuchTypeException;

import java.util.Locale;

public abstract class AbstractRepositoryFactory {
    public static RepositoryFactory createFactory(String type) {
        RepositoryFactory result;
        switch (type.toLowerCase(Locale.ROOT)) {
            case "patient":
                result = new PatientRepositoryFactory();
                break;
            case "employee":
                result = new EmployeeRepositoryFactory();
                break;
            default:
                throw new NoSuchTypeException("Such RepositoryFactory type does not exist: %s".formatted(type));
        }
        return result;
    }
}
