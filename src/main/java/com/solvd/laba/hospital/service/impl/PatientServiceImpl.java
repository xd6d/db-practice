package com.solvd.laba.hospital.service.impl;

import com.solvd.laba.hospital.dao.repository.PatientRepository;
import com.solvd.laba.hospital.dao.repository.impl.PatientRepositoryImpl;
import com.solvd.laba.hospital.model.exceptions.IncorrectPersonException;
import com.solvd.laba.hospital.model.person.PatientPerson;
import com.solvd.laba.hospital.service.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class PatientServiceImpl implements PatientService {
    private static final Logger LOGGER = LogManager.getLogger(PatientServiceImpl.class);

    private final PatientRepository patientRepository;
    private final AnalysisService analysisService;
    private final HospitalizationService hospitalizationService;
    private final VaccinationService vaccinationService;
    private final DeclarationService declarationService;

    public PatientServiceImpl() {
        this.patientRepository = new PatientRepositoryImpl();
        this.analysisService = new AnalysisServiceImpl();
        this.hospitalizationService = new HospitalizationServiceImpl();
        this.vaccinationService = new VaccinationServiceImpl();
        this.declarationService = new DeclarationServiceImpl();
    }

    @Override
    public PatientPerson add(PatientPerson patient) {
        try {
            validate(patient);
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
        patient.setDeclaration(declarationService.getByPatientId(patient.getId()));
    }

    @Override
    public void update(PatientPerson patient) {
        try {
            validate(patient);
            patientRepository.updateById(patient);
        } catch (IncorrectPersonException e) {
            LOGGER.error(e);
        }
    }

    @Override
    public void delete(long id) {
        patientRepository.deleteById(id);
    }

    private void validate(PatientPerson patient) throws IncorrectPersonException {
        if (patient.getName() == null) {
            throw new IncorrectPersonException("Name can not be null");
        }
        if (patient.getSurname() == null) {
            throw new IncorrectPersonException("Surname can not be null");
        }
        if (patient.getPhoneNumber() == null) {
            throw new IncorrectPersonException("Phone number can not be null");
        }
        if (patient.getEmail() == null) {
            throw new IncorrectPersonException("Email can not be null");
        }
        Optional<PatientPerson> gotById = getById(patient.getId());
        List<PatientPerson> all = getAll();
        List<PatientPerson> repeats = all.stream()
                .filter(p -> p.getPhoneNumber().equals(patient.getPhoneNumber()) ||
                        p.getEmail().equals(patient.getEmail()))
                .toList();
        if (!repeats.isEmpty() && (gotById.isEmpty() || repeats.stream().anyMatch(p -> p.getId() != patient.getId()))) {
            throw new IncorrectPersonException("Phone number or email is already registered");
        }
    }
}
