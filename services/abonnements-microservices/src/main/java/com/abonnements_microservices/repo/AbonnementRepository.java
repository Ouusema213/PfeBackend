package com.abonnements_microservices.repo;

import com.abonnements_microservices.model.Abonnement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AbonnementRepository extends JpaRepository<Abonnement, Long> {
    List<Abonnement> findByNomContainingIgnoreCase(String nom);
}
