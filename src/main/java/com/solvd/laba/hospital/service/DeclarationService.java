package com.solvd.laba.hospital.service;

import com.solvd.laba.hospital.model.info.Declaration;

public interface DeclarationService {
    Declaration getByPatientId(long id);
}
