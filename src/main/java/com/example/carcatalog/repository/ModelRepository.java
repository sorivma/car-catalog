package com.example.carcatalog.repository;

import com.example.carcatalog.entity.Model;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ModelRepository extends JpaRepository<Model, UUID> {
}
