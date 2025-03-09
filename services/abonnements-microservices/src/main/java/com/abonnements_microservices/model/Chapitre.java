package com.abonnements_microservices.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Chapitre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomChapitre;
    private String nomDeProf ;
    private int Duree ;
    private int nombreDeCours ;

    public String getNomDeProf() {
        return nomDeProf;
    }

    public void setNomDeProf(String nomDeProf) {
        this.nomDeProf = nomDeProf;
    }

    public int getDuree() {
        return Duree;
    }

    public void setDuree(int duree) {
        Duree = duree;
    }

    public int getNombreDeCours() {
        return nombreDeCours;
    }

    public void setNombreDeCours(int nombreDeCours) {
        this.nombreDeCours = nombreDeCours;
    }

    @ManyToOne
    @JoinColumn(name = "matiere_id")
    @JsonBackReference
    private Matiere matiere;

    public Matiere getMatiere() {
        return matiere;
    }

    public void setMatiere(Matiere matiere) {
        this.matiere = matiere;
    }

    public List<Cours> getCours() {
        return cours;
    }

    public void setCours(List<Cours> cours) {
        this.cours = cours;
    }

    @OneToMany(mappedBy = "chapitre", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Cours> cours ;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomChapitre() {
        return nomChapitre;
    }

    public void setNomChapitre(String nomChapitre) {
        this.nomChapitre = nomChapitre;
    }
}