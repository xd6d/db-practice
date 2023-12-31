package com.solvd.laba.hospital.service.person.impl;

import com.solvd.laba.hospital.dao.repository.person.PatientRepository;
import com.solvd.laba.hospital.dao.repository.person.impl.PatientRepositoryMybatisImpl;
import com.solvd.laba.hospital.model.exceptions.IncorrectPersonException;
import com.solvd.laba.hospital.model.person.PatientPerson;
import com.solvd.laba.hospital.service.info.*;
import com.solvd.laba.hospital.service.info.impl.*;
import com.solvd.laba.hospital.service.person.PatientService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class PatientServiceImpl extends PersonService implements PatientService {
    private static final Logger LOGGER = LogManager.getLogger(PatientServiceImpl.class);

    private final PatientRepository patientRepository;
    private final AnalysisService analysisService;
    private final HospitalizationService hospitalizationService;
    private final VaccinationService vaccinationService;
    private final AllergyService allergyService;
    private final DeclarationService declarationService;

    public PatientServiceImpl() {
        this.patientRepository = new PatientRepositoryMybatisImpl();
//        this.patientRepository = new PatientRepositoryJdbcImpl();
        this.analysisService = new AnalysisServiceImpl();
        this.hospitalizationService = new HospitalizationServiceImpl();
        this.vaccinationService = new VaccinationServiceImpl();
        this.allergyService = new AllergyServiceImpl();
        this.declarationService = new DeclarationServiceImpl();
    }

    @Override
    public PatientPerson add(PatientPerson patient) {
        try {
            validateForNull(patient);
            validateUniqueness(patient, getAll(), false);
            patientRepository.create(patient);
        } catch (IncorrectPersonException e) {
            LOGGER.error(e);
        }
        return patient;
    }

    @Override
    public List<PatientPerson> getAll() {
        List<PatientPerson> all = patientRepository.findAll();
        all.forEach(this::fillPatient);
        return all;
    }

    @Override
    public Optional<PatientPerson> getById(long id) {
        Optional<PatientPerson> optionalPatient = patientRepository.findById(id);
        optionalPatient.ifPresent(this::fillPatient);
        return optionalPatient;
    }

    private void fillPatient(PatientPerson patient) {
        patient.setAnalyses(analysisService.getAllByPatientId(patient.getId()));
        patient.setHospitalizations(hospitalizationService.getAllByPatientId(patient.getId()));
        patient.setVaccinations(vaccinationService.getAllByPatientId(patient.getId()));
        patient.setAllergies(allergyService.getAllByPatientId(patient.getId()));
        patient.setDeclaration(declarationService.getByPatientId(patient.getId()));
    }

    @Override
    public void update(PatientPerson patient) {
        try {
            validateForNull(patient);
            validateUniqueness(patient, getAll(), true);
            patientRepository.update(patient);
        } catch (IncorrectPersonException e) {
            LOGGER.error(e);
        }
    }

    @Override
    public void deleteById(long id) {
        patientRepository.deleteById(id);
    }
}
