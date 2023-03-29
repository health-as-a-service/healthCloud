package tn.esprit.healthcloud.services;

import tn.esprit.healthcloud.entities.Administration;

import java.util.List;

public interface IAdministrationService {
    Administration ajouter (Administration administration);
    Administration modifier (Administration administration, int id);
    List<Administration> afficher ();
    Administration afficherById(int id);
    void supprimer (int id);
}