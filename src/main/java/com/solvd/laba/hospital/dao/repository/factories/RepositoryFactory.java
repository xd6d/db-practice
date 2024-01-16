package com.solvd.laba.hospital.dao.repository.factories;

import com.solvd.laba.hospital.dao.repository.Repository;

public interface RepositoryFactory {
    Repository createRepository(String type);
}
