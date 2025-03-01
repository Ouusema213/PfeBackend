package com.abonnements_microservices.services;
import com.abonnements_microservices.model.Chapitre;
import com.abonnements_microservices.model.Cours;
import com.abonnements_microservices.repo.ChapitreRepository;
import com.abonnements_microservices.repo.CoursRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CoursService {
    @Autowired
    private CoursRepository coursRepository;
    @Autowired
    private ChapitreRepository chapitreRepository;

    public List<Cours> getAllCours() {
        return coursRepository.findAll();
    }

    public Optional<Cours> getCoursById(Long id) {
        return coursRepository.findById(id);
    }

    public List<Cours> getCoursByChapitre(Long chapitreId) {
        return coursRepository.findByChapitreId(chapitreId);
    }

    public Cours createCours(Cours cours, Long chapitreId) {
        Optional<Chapitre> chapitre = chapitreRepository.findById(chapitreId);
        if (chapitre.isPresent()) {
            cours.setChapitre(chapitre.get());
            return coursRepository.save(cours);
        }
        throw new RuntimeException("Chapitre non trouvé");
    }

    public Cours updateCours(Long id, Cours updatedCours) {
        if (coursRepository.existsById(id)) {

            return coursRepository.save(updatedCours);
        }
        return null;
    }

    public void deleteCours(Long id) {
        coursRepository.deleteById(id);
    }

    public Cours addCours(Cours cours) {
        return coursRepository.save(cours);

    }
}

