package com.abonnements_microservices.repo;

import com.abonnements_microservices.model.Niveau;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NiveauRepository extends JpaRepository<Niveau, Long> {
}
