package com.abonnements_microservices.controller;

import com.abonnements_microservices.model.Matiere;
import com.abonnements_microservices.services.MatiereService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/matieres")

@CrossOrigin(origins = {"http://localhost:3036", "http://localhost:3000"})

public class MatiereController {
    @Autowired
    private MatiereService matiereService;

    @GetMapping
    public List<Matiere> getAllMatieres() {
        return matiereService.getAllMatieres();
    }

    @GetMapping("/{id}")
    public Optional<Matiere> getMatiereById(@PathVariable Long id) {
        return matiereService.getMatiereById(id);
    }

    @PostMapping("/{abonnementId}")
    public Matiere createMatiere(@RequestBody Matiere matiere, @PathVariable Long abonnementId) {
        return matiereService.createMatiere(matiere, abonnementId);
    }

    @PutMapping("/{id}")
    public Matiere updateMatiere(@PathVariable Long id, @RequestBody Matiere matiere) {
        return matiereService.updateMatiere(id, matiere);
    }

    @DeleteMapping("/{id}")
    public void deleteMatiere(@PathVariable Long id) {
        matiereService.deleteMatiere(id);
    }

    @PostMapping("/add")
    public Matiere addMatiere(@RequestBody Matiere matiere) {

        return matiereService.addMatiere(matiere);
    }
}

