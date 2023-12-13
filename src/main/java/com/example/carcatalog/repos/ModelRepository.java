package com.example.carcatalog.repos;

import com.example.carcatalog.entity.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ModelRepository extends JpaRepository<Model, UUID> {
    @Query("""
            SELECT m
            FROM Model m
            WHERE m.brand.name = :brandName""")
    List<Model> findByBrandName(String brandName);
}
