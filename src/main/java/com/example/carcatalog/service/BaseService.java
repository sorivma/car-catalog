package com.example.carcatalog.service;

import java.util.List;

public interface BaseService<T, ID> {
    List<T> findAll();

    T findById(ID id);

    T add(T dto);

}
