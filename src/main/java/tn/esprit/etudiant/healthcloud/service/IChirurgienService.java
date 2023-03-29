package tn.esprit.etudiant.healthcloud.service;

import tn.esprit.etudiant.healthcloud.entities.Chirurgien;

import java.util.List;

public interface IChirurgienService {
    Chirurgien ajouter (Chirurgien chirurgien);
    Chirurgien modifier (Chirurgien chirurgien, int id);
    List<Chirurgien> afficher ();
    Chirurgien afficherById(int id);
    void supprimer (int id);
}