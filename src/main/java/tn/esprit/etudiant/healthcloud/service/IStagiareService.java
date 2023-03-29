package tn.esprit.etudiant.healthcloud.service;

import tn.esprit.etudiant.healthcloud.entities.Stagiare;

import java.util.List;

public interface IStagiareService {
    Stagiare ajouter (Stagiare stagiare);
    Stagiare modifier (Stagiare stagiare, int id);
    List<Stagiare> afficher ();
    Stagiare afficherById(int id);
    void supprimer (int id);
}