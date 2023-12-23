package com.solvd.laba.hospital.service.info.impl;

import com.solvd.laba.hospital.dao.repository.info.VaccinationRepository;
import com.solvd.laba.hospital.dao.repository.info.impl.VaccinationRepositoryImpl;
import com.solvd.laba.hospital.model.info.Vaccination;
import com.solvd.laba.hospital.service.info.VaccinationService;

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
