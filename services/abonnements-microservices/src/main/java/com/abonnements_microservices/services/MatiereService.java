package com.abonnements_microservices.services;
import com.abonnements_microservices.model.Abonnement;
import com.abonnements_microservices.model.Matiere;
import com.abonnements_microservices.repo.AbonnementRepository;
import com.abonnements_microservices.repo.MatiereRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class MatiereService {
    @Autowired
    private MatiereRepository matiereRepository;
    @Autowired
    private AbonnementRepository abonnementRepository;

    public List<Matiere> getAllMatieres() {
        return matiereRepository.findAll();
    }

    public Optional<Matiere> getMatiereById(Long id) {
        return matiereRepository.findById(id);
    }

    public Matiere createMatiere(Matiere matiere, Long abonnementId) {
        Optional<Abonnement> abonnement = abonnementRepository.findById(abonnementId);
        if (abonnement.isPresent()) {
            matiere.setAbonnement(abonnement.get());
            return matiereRepository.save(matiere);
        }
        throw new RuntimeException("Abonnement non trouvé");
    }

    public Matiere updateMatiere(Long id, Matiere updatedMatiere) {
        if (matiereRepository.existsById(id)) {

            return matiereRepository.save(updatedMatiere);
        }
        return null;
    }

    public void deleteMatiere(Long id) {
        matiereRepository.deleteById(id);
    }

    public Matiere addMatiere(Matiere matier) {
        return matiereRepository.save(matier);
    }
}

