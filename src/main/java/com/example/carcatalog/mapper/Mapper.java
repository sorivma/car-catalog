package com.example.carcatalog.mapper;

public interface Mapper<M, D> {
    M toModel(D dto);

    D toDTO(M model);
}
