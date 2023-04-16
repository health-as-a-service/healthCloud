package tn.esprit.healthcloud.services;

import tn.esprit.healthcloud.entities.Patient;

import java.util.List;

public interface IPatientService {
    Patient ajouterPatient(Patient patient);

    Patient modifierPatient(Patient patient);

    List<Patient> afficherPatients();

    Patient afficherPatient(int id);

    void supprimerPatient(int id);

    Patient assignerDossierToPatient(Integer idP, Integer idD);

}
