package tn.esprit.healthcloud.services;

import tn.esprit.healthcloud.entities.Stagiare;

import java.util.List;

public interface IStagiareService {
    Stagiare ajouter (Stagiare stagiare);
    Stagiare modifier (Stagiare stagiare, int id);
    List<Stagiare> afficher ();
    Stagiare afficherById(int id);
    void supprimer (int id);
}