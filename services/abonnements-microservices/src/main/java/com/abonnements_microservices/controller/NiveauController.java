package com.abonnements_microservices.controller;


import com.abonnements_microservices.model.Niveau;
import com.abonnements_microservices.services.NiveauService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/niveaux")
@CrossOrigin(origins = {"http://localhost:3036", "http://localhost:3000"})
public class NiveauController {
    @Autowired
    private NiveauService niveauService;

    @GetMapping("/all")
    public List<Niveau> getAllNiveaux() {
        return niveauService.getAllNiveaux();
    }

    @GetMapping("/{id}")
    public Optional<Niveau> getNiveauById(@PathVariable Long id) {
        return niveauService.getNiveauById(id);
    }

    @PostMapping("/add")
    public Niveau createNiveau(@RequestBody Niveau niveau) {
        return niveauService.createNiveau(niveau);
    }

    @DeleteMapping("/{id}")
    public void deleteNiveau(@PathVariable Long id) {
        niveauService.deleteNiveau(id);
    }
    @PutMapping("/{id}")
    public Niveau updateNiveau(@PathVariable Long id,@RequestBody Niveau niveau) {
        return niveauService.updateNiveau(id, niveau);
    }
}
