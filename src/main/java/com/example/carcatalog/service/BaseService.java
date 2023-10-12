package com.example.carcatalog.service;

import java.util.Optional;
import java.util.Set;

public interface BaseService<T, ID> {
    Set<T> findAll();

    Optional<T> findById(ID id);

    T add(T dto);
}
