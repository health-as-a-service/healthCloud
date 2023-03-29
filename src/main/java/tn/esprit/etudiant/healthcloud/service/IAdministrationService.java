package tn.esprit.etudiant.healthcloud.service;

import tn.esprit.etudiant.healthcloud.entities.Administration;

import java.util.List;

public interface IAdministrationService {
    Administration ajouter (Administration administration);
    Administration modifier (Administration administration, int id);
    List<Administration> afficher ();
    Administration afficherById(int id);
    void supprimer (int id);
}