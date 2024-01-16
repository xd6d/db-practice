package com.solvd.laba.hospital.dao.repository.factories;

import com.solvd.laba.hospital.dao.repository.Repository;
import com.solvd.laba.hospital.dao.repository.person.PatientRepository;
import com.solvd.laba.hospital.dao.repository.person.impl.PatientRepositoryJdbcImpl;
import com.solvd.laba.hospital.dao.repository.person.impl.PatientRepositoryMybatisImpl;
import com.solvd.laba.hospital.model.exceptions.NoSuchTypeException;
import com.solvd.laba.hospital.model.person.PatientPerson;

import java.util.Locale;

public class PatientRepositoryFactory implements RepositoryFactory {
    @Override
    public Repository<PatientPerson> createRepository(String type) {
        PatientRepository result;
        switch (type.toLowerCase(Locale.ROOT)) {
            case "jdbc":
                result = new PatientRepositoryJdbcImpl();
                break;
            case "mybatis":
                result = new PatientRepositoryMybatisImpl();
                break;
            default:
                throw new NoSuchTypeException("Such repository type does not exist: %s".formatted(type));
        }
        return result;
    }
}
