package com.abonnements_microservices.controller;

import com.abonnements_microservices.model.Cours;
import com.abonnements_microservices.repo.CoursRepository;
import com.abonnements_microservices.services.CoursService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cours")
@CrossOrigin(origins = {"http://localhost:3036", "http://localhost:3000"})
public class CoursController {
    @Autowired
    private CoursService coursService;

    @Autowired
    private CoursRepository coursRepository;

    @GetMapping
    public List<Cours> getAllCours() {
        return coursService.getAllCours();
    }

    @GetMapping("/{id}")
    public Optional<Cours> getCoursById(@PathVariable Long id) {
        return coursService.getCoursById(id);
    }

    @GetMapping("/chapitre/{chapitreId}")
    public List<Cours> getCoursByChapitre(@PathVariable Long chapitreId) {
        return coursService.getCoursByChapitre(chapitreId);
    }

    @PostMapping("/{chapitreId}")
    public Cours createCours(@RequestBody Cours cours, @PathVariable Long chapitreId) {
        return coursService.createCours(cours, chapitreId);
    }

    @PostMapping("/create")
    public ResponseEntity<Cours> createCours(@RequestBody Cours cours) {
        try {
            Cours newCours = coursRepository.save(cours);
            return new ResponseEntity<>(newCours, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @PutMapping("/{id}")
    public Cours updateCours(@PathVariable Long id, @RequestBody Cours cours) {
        return coursService.updateCours(id, cours);
    }

    @DeleteMapping("/{id}")
    public void deleteCours(@PathVariable Long id) {
        coursService.deleteCours(id);
    }

    @PostMapping("/add")
    public Cours addCours(@RequestBody Cours cours) {
        System.out.println("Cours reçu : " + cours);
        return coursService.addCours(cours);
    }
}

