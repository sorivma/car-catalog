package com.example.carcatalog.repos;

import com.example.carcatalog.entity.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OfferRepository extends JpaRepository<Offer, UUID> {
    @Query("select o from Offer o join User u where u.isActive = true ")
    List<Offer> findAllActive();
}
