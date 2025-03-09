package com.abonnements_microservices.controller;

import com.abonnements_microservices.services.AbonnementService;
import com.abonnements_microservices.services.CoursService;
import com.abonnements_microservices.services.NiveauService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/statistics")
@CrossOrigin(origins = {"http://localhost:3036", "http://localhost:3000"})
public class StatisticsController {

    @Autowired
    private AbonnementService abonnementService;

    @Autowired
    private NiveauService niveauService;

    @Autowired
    private CoursService coursService;

    @GetMapping
    public Map<String, Long> getStatistics() {
        Map<String, Long> stats = new HashMap<>();
        stats.put("totalAbonnements", abonnementService.countAbonnements());
        stats.put("totalNiveaux", niveauService.countNiveaux());
        stats.put("totalCours", coursService.countCours());
        return stats;
    }
}

