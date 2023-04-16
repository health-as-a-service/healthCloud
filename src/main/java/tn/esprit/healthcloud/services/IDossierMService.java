package tn.esprit.healthcloud.services;

import tn.esprit.healthcloud.entities.DossierMedical;

import java.util.List;

public interface IDossierMService {
    DossierMedical ajouterDMedical (DossierMedical dossierMedical);
    DossierMedical modifierDMedical (DossierMedical dossierMedical);
    List<DossierMedical> afficherDMedicaux();
    DossierMedical afficherDMedical(int id);
    void supprimerDMedical(int id);
}
