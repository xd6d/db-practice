package com.solvd.laba.hospital;

import com.solvd.laba.hospital.model.person.PatientPerson;
import com.solvd.laba.hospital.service.PatientService;
import com.solvd.laba.hospital.service.impl.PatientServiceImpl;

public class Demo {

    public static void main(String[] args) {
        PatientPerson patient = new PatientPerson();
        patient.setName("test");
        patient.setSurname("test");
        patient.setPhoneNumber("test");
        patient.setEmail("test");
        PatientService patientService = new PatientServiceImpl();
//        System.out.println(patientService.add(patient));
//        System.out.println(patient.getId());
//        System.out.println(patientService.getById(11));
//        patient.setId(11);
//        patient.setPhoneNumber("+22222");
//        patient.setEmail("test@ASD");
//        patient.setName("test-update");
//        patient.setSurname("test-update");
//        patientService.delete(11);
    }
}
