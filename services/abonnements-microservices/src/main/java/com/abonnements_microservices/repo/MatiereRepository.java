package com.abonnements_microservices.repo;

import com.abonnements_microservices.model.Matiere;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatiereRepository extends JpaRepository<Matiere, Long> {
    List<Matiere> findByNomMatiereContainingIgnoreCase(String nomMatiere);
}
