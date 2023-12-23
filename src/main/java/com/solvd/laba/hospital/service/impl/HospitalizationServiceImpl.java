package com.solvd.laba.hospital.service.impl;

import com.solvd.laba.hospital.dao.repository.HospitalizationRepository;
import com.solvd.laba.hospital.dao.repository.impl.HospitalizationRepositoryImpl;
import com.solvd.laba.hospital.model.info.Hospitalization;
import com.solvd.laba.hospital.service.HospitalizationService;

import java.util.List;

public class HospitalizationServiceImpl implements HospitalizationService {
    private final HospitalizationRepository hospitalizationRepository;

    public HospitalizationServiceImpl() {
        this.hospitalizationRepository = new HospitalizationRepositoryImpl();
    }

    @Override
    public List<Hospitalization> getAllByPatientId(long id) {
        return hospitalizationRepository.findAllByPatientId(id);
    }
}
