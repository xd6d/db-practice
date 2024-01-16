package com.solvd.laba.hospital.dao.repository;

import java.util.List;
import java.util.Optional;

public interface Repository<T> {
    void create(T entity);

    List<T> findAll();

    Optional<T> findById(long id);

    void update(T entity);

    void deleteById(long id);
}
