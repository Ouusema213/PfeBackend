package com.abonnements_microservices.services;

import com.abonnements_microservices.model.Abonnement;
import com.abonnements_microservices.repo.AbonnementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AbonnementService {
    @Autowired
    private AbonnementRepository abonnementRepository;

    public List<Abonnement> getAllAbonnements() {
        return abonnementRepository.findAll();
    }
    public Optional<Abonnement> getAbonnementById(Long id) {
        return abonnementRepository.findById(id);
    }
    public List<Abonnement> getAbonnementsByNom(String nom) {
        return abonnementRepository.findByNomContainingIgnoreCase(nom);
    }

    public Abonnement addAbonnement(Abonnement abonnement) {
        return abonnementRepository.save(abonnement);
    }

    public Abonnement updateAbonnement(Long id, Abonnement newAbonnement) {
        return abonnementRepository.findById(id).map(abonnement -> {
            abonnement.setNom(newAbonnement.getNom());
            abonnement.setDescription(newAbonnement.getDescription());
            abonnement.setPrix(newAbonnement.getPrix());
            abonnement.setImage(newAbonnement.getImage());
            return abonnementRepository.save(abonnement);
        }).orElseThrow(() -> new RuntimeException("Abonnement non trouvé"));
    }

    public String deleteAbonnement(Long id) {
        abonnementRepository.deleteById(id);
        return "Abonnement supprimé avec succès";
    }
}