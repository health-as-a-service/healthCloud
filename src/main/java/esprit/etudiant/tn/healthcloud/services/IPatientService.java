package esprit.etudiant.tn.healthcloud.services;

import esprit.etudiant.tn.healthcloud.entities.Patient;

import java.util.List;

public interface IPatientService {
    List<Patient> getAllPatients();
    Patient getPatientById(int id);
    Patient saveOrUpdatePatient(Patient patient);
    void deletePatient(int id);
}

