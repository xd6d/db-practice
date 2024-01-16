package com.solvd.laba.hospital.service;

public interface Listener<T> {
    void onNew(T entity);

    void onDelete(T entity);
}
