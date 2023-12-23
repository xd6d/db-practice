package com.solvd.laba.hospital.service.info.impl;

import com.solvd.laba.hospital.dao.repository.info.HospitalizationRepository;
import com.solvd.laba.hospital.dao.repository.info.impl.HospitalizationRepositoryImpl;
import com.solvd.laba.hospital.model.info.Hospitalization;
import com.solvd.laba.hospital.service.info.HospitalizationService;

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
