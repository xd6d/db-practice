package com.solvd.laba.hospital.model.person;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
public class EmployeePerson extends Person {
    private String degree;
    private Position position;

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private final EmployeePerson instance;

        private Builder() {
            this.instance = new EmployeePerson();
        }

        public Builder id(long id) {
            instance.setId(id);
            return this;
        }

        public Builder name(String name) {
            instance.setName(name);
            return this;
        }

        public Builder surname(String surname) {
            instance.setSurname(surname);
            return this;
        }

        public Builder phoneNumber(String phoneNumber) {
            instance.setPhoneNumber(phoneNumber);
            return this;
        }

        public Builder email(String email) {
            instance.setEmail(email);
            return this;
        }

        public Builder degree(String degree) {
            instance.degree = degree;
            return this;
        }

        public Builder position(Position position) {
            instance.position = position;
            return this;
        }

        public EmployeePerson build() {
            return instance;
        }
    }
}
