package tn.esprit.etudiant.healthcloud.service;

import tn.esprit.etudiant.healthcloud.entities.Medecin;

import java.util.List;

public interface IMedecinService {
    Medecin ajouter (Medecin medecin);
    Medecin modifier (Medecin medecin, int id);
    List<Medecin> afficher ();
    Medecin afficherById(int id);
    void supprimer (int id);
}