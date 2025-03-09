package com.abonnements_microservices.services;

import com.abonnements_microservices.model.Matiere;
import com.abonnements_microservices.model.Niveau;
import com.abonnements_microservices.repo.NiveauRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NiveauService {
    @Autowired
    private NiveauRepository niveauRepository;

    public List<Niveau> getAllNiveaux() {
        return niveauRepository.findAll();
    }

    public Optional<Niveau> getNiveauById(Long id) {
        return niveauRepository.findById(id);
    }

    public Niveau createNiveau(Niveau niveau) {
        return niveauRepository.save(niveau);
    }

    public void deleteNiveau(Long id) {
        niveauRepository.deleteById(id);
    }
    public Niveau updateNiveau(Long id ,Niveau niveau) {
        return  niveauRepository.save(niveau);
    }

    public long countNiveaux() {
        return niveauRepository.count();
    }
}

