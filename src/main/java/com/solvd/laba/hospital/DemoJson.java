package com.solvd.laba.hospital;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.solvd.laba.hospital.model.person.PatientPerson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;

public class DemoJson {
    public static final Logger LOGGER = LogManager.getLogger(DemoJson.class);

    public static void main(String[] args) {
        File file = new File("src/main/resources/json/patient.json");

        ObjectMapper mapper = new ObjectMapper();
        try {
            PatientPerson patient = mapper.readValue(file, PatientPerson.class);
            LOGGER.info(patient);
        } catch (IOException e) {
            LOGGER.error(e);
        }

    }
}
