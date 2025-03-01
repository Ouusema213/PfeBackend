package com.abonnements_microservices.repo;

import com.abonnements_microservices.model.Cours;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoursRepository extends JpaRepository<Cours, Long> {
    List<Cours> findByChapitreId(Long chapitreId);
}
