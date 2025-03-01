package com.abonnements_microservices.controller;

import com.abonnements_microservices.model.Abonnement;
import com.abonnements_microservices.services.AbonnementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/abonnements")
@CrossOrigin(origins = {"http://localhost:3036", "http://localhost:3000"})
public class AbonnementController {
    @Autowired
    private AbonnementService abonnementService;

    @GetMapping("/all")
    public List<Abonnement> getAllAbonnements() {
        return abonnementService.getAllAbonnements();
    }

    @GetMapping("/{id}")
    public Optional<Abonnement> getAbonnementById(@PathVariable Long id) {
        return abonnementService.getAbonnementById(id);
    }

    @GetMapping("/search")
    public List<Abonnement> getAbonnementsByNom(@RequestParam String nom) {
        return abonnementService.getAbonnementsByNom(nom);
    }

    @PostMapping("/add")
    public Abonnement addAbonnement(@RequestBody Abonnement abonnement) {
        return abonnementService.addAbonnement(abonnement);
    }

    @PutMapping("/update/{id}")
    public Abonnement updateAbonnement(@PathVariable Long id, @RequestBody Abonnement abonnement) {
        return abonnementService.updateAbonnement(id, abonnement);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteAbonnement(@PathVariable Long id) {
        return abonnementService.deleteAbonnement(id);
    }
}
