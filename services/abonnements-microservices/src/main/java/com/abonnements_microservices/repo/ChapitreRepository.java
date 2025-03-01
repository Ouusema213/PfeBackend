package com.abonnements_microservices.repo;

import com.abonnements_microservices.model.Chapitre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChapitreRepository extends JpaRepository<Chapitre, Long> {
    List<Chapitre> findByMatiere_IdMatiere(Long matiereId);

}
