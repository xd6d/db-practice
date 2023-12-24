package com.solvd.laba.hospital;

import com.solvd.laba.hospital.service.person.impl.PatientServiceImpl;

public class Demo {

    public static void main(String[] args) {
        System.out.println(new PatientServiceImpl().getById(1));
    }
}
