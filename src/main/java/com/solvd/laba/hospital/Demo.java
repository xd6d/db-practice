package com.solvd.laba.hospital;

import com.solvd.laba.hospital.model.person.EmployeePerson;
import com.solvd.laba.hospital.model.person.Position;
import com.solvd.laba.hospital.service.person.EmployeeService;
import com.solvd.laba.hospital.service.person.impl.EmployeeServiceImpl;

public class Demo {

    public static void main(String[] args) {
        EmployeeService employeeService = new EmployeeServiceImpl();
        EmployeePerson employee = new EmployeePerson();
        //2,Maria,Tree,+12312312,mmaarriiaa@mail.com,
        employee.setId(7);
        employee.setName("Maria");
        employee.setSurname("Tree");
        employee.setPhoneNumber("+12312312");
        employee.setEmail("mmaarriiaa@mail.com");
        employee.setPosition(Position.NURSE);
        employeeService.add(employee);
    }
}
