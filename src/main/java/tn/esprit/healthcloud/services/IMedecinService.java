package tn.esprit.healthcloud.services;

import tn.esprit.healthcloud.entities.Medecin;

import java.util.List;

public interface IMedecinService {
    Medecin ajouter (Medecin medecin);
    Medecin modifier (Medecin medecin, int id);
    List<Medecin> afficher ();
    Medecin afficherById(int id);
    void supprimer (int id);
}