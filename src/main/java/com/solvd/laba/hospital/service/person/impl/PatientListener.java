package com.solvd.laba.hospital.service.person.impl;

import com.solvd.laba.hospital.model.person.PatientPerson;
import com.solvd.laba.hospital.service.Listener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PatientListener implements Listener<PatientPerson> {
    public static final Logger LOGGER = LogManager.getLogger(PatientListener.class);

    @Override
    public void onNew(PatientPerson patient) {
        LOGGER.info("New patient registered: " + patient);
    }

    @Override
    public void onDelete(PatientPerson patient) {
        LOGGER.info("Patient deleted his account: " + patient);
    }
}
