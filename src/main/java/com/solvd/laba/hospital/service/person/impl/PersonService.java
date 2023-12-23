package com.solvd.laba.hospital.service.person.impl;

import com.solvd.laba.hospital.model.exceptions.IncorrectPersonException;
import com.solvd.laba.hospital.model.person.Person;

import java.util.List;

public abstract class PersonService {
    void validateForNull(Person person) throws IncorrectPersonException {
        if (person.getName() == null) {
            throw new IncorrectPersonException("Name can not be null");
        }
        if (person.getSurname() == null) {
            throw new IncorrectPersonException("Surname can not be null");
        }
        if (person.getPhoneNumber() == null) {
            throw new IncorrectPersonException("Phone number can not be null");
        }
        if (person.getEmail() == null) {
            throw new IncorrectPersonException("Email can not be null");
        }
    }

    void validateUniqueness(Person person, List<? extends Person> all, boolean exists) throws IncorrectPersonException {
        List<? extends Person> repeats = all.stream()
                .filter(p -> p.getPhoneNumber().equals(person.getPhoneNumber()) ||
                        p.getEmail().equals(person.getEmail()))
                .toList();
        if (!repeats.isEmpty() && (!exists || repeats.stream().anyMatch(p -> p.getId() != person.getId()))) {
            throw new IncorrectPersonException("Phone number or email is already registered");
        }
    }
}
