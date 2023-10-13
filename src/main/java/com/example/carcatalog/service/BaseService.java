package com.example.carcatalog.service;

import com.example.carcatalog.dto.RoleDTO;

import java.util.List;
import java.util.Optional;

public interface BaseService<T, ID> {
    List<RoleDTO> findAll();

    T findById(ID id);

    T add(T dto);

}
