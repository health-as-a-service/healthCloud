package tn.esprit.etudiant.healthcloud.service;

import tn.esprit.etudiant.healthcloud.entities.Infirmier;

import java.util.List;

public interface IInfirmierService {
    Infirmier ajouter (Infirmier infirmier);
    Infirmier modifier (Infirmier infirmier, int id);
    List<Infirmier> afficher ();
    Infirmier afficherById(int id);
    void supprimer (int id);
}