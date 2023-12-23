package com.solvd.laba.hospital.service.impl;

import com.solvd.laba.hospital.dao.repository.VaccinationRepository;
import com.solvd.laba.hospital.dao.repository.impl.VaccinationRepositoryImpl;
import com.solvd.laba.hospital.model.info.Vaccination;
import com.solvd.laba.hospital.service.VaccinationService;

import java.util.List;

public class VaccinationServiceImpl implements VaccinationService {
    private final VaccinationRepository vaccinationRepository;

    public VaccinationServiceImpl() {
        this.vaccinationRepository = new VaccinationRepositoryImpl();
    }

    @Override
    public List<Vaccination> getAllByPatientId(long id) {
        return vaccinationRepository.findAllByPatientId(id);
    }
}
