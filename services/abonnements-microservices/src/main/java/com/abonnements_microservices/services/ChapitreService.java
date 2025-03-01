package com.abonnements_microservices.services;

import com.abonnements_microservices.model.Chapitre;
import com.abonnements_microservices.model.Matiere;
import com.abonnements_microservices.repo.ChapitreRepository;
import com.abonnements_microservices.repo.MatiereRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ChapitreService {
    @Autowired
    private ChapitreRepository chapitreRepository;

    @Autowired
    private MatiereRepository matiereRepository;

    public static void main(String[] args) {

    }

    public List<Chapitre> getAllChapitres() {
        return chapitreRepository.findAll();
    }

    public Optional<Chapitre> getChapitreById(Long id) {
        return chapitreRepository.findById(id);
    }

    public List<Chapitre> getChapitresByMatiere(Long matiereId) {
        return chapitreRepository.findByMatiere_IdMatiere(matiereId);
    }

    public Chapitre createChapitre(Chapitre chapitre, Long matiereId) {
        Matiere matiere = matiereRepository.findById(matiereId)
                .orElseThrow(() -> new RuntimeException("Matière non trouvée"));

        chapitre.setMatiere(matiere); // Associer la matière
        return chapitreRepository.save(chapitre);
    }


    public Chapitre updateChapitre(Long id, Chapitre updatedChapitre) {
        if (chapitreRepository.existsById(id)) {
            updatedChapitre.setId(id);
            return chapitreRepository.save(updatedChapitre);
        }
        return null;
    }

    public void deleteChapitre(Long id) {
        chapitreRepository.deleteById(id);
    }

    public Chapitre addChapitre(Chapitre chapitre) {
        return chapitreRepository.save(chapitre);
    }
}

