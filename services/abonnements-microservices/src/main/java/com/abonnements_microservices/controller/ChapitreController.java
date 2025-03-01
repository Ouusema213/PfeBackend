package com.abonnements_microservices.controller;

import com.abonnements_microservices.model.Chapitre;
import com.abonnements_microservices.services.ChapitreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/chapitres")
@CrossOrigin(origins = {"http://localhost:3036", "http://localhost:3000"})
public class ChapitreController {
    @Autowired
    private ChapitreService chapitreService;

    @GetMapping("/all")
    public List<Chapitre> getAllChapitres() {
        return chapitreService.getAllChapitres();
    }

    @GetMapping("/{id}")
    public Optional<Chapitre> getChapitreById(@PathVariable Long id) {
        return chapitreService.getChapitreById(id);
    }

    @GetMapping("/matiere/{matiereId}")
    public List<Chapitre> getChapitresByMatiere(@PathVariable Long matiereId) {
        return chapitreService.getChapitresByMatiere(matiereId);
    }

    @PostMapping("/{matiereId}")
    public Chapitre createChapitre(@RequestBody Chapitre chapitre, @PathVariable Long matiereId) {
        return chapitreService.createChapitre(chapitre, matiereId);
    }


    @PutMapping("/{id}")
    public Chapitre updateChapitre(@PathVariable Long id, @RequestBody Chapitre chapitre) {
        return chapitreService.updateChapitre(id, chapitre);
    }

    @DeleteMapping("/{id}")
    public void deleteChapitre(@PathVariable Long id) {
        chapitreService.deleteChapitre(id);
    }

    @PostMapping("/add")
    public Chapitre addChapitre(@RequestBody Chapitre chapitre) {
        return chapitreService.addChapitre(chapitre);
    }
}

