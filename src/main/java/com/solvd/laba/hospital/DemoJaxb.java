package com.solvd.laba.hospital;

import com.solvd.laba.hospital.model.person.PatientPerson;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;

public class DemoJaxb {
    public static final Logger LOGGER = LogManager.getLogger(DemoJaxb.class);

    public static void main(String[] args) {
        File file = new File("src/main/resources/xml/person/patient.xml");

        try {
            JAXBContext context = JAXBContext.newInstance(PatientPerson.class);
            Unmarshaller patientUnmarshaller = context.createUnmarshaller();
            PatientPerson patient = (PatientPerson) patientUnmarshaller.unmarshal(file);
            LOGGER.info(patient);
        } catch (JAXBException e) {
            LOGGER.error(e);
        }
    }
}
