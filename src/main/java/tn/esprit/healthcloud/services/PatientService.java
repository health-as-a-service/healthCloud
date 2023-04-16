package tn.esprit.healthcloud.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.healthcloud.entities.DossierMedical;
import tn.esprit.healthcloud.entities.Patient;
import tn.esprit.healthcloud.repositories.DossierMRepository;
import tn.esprit.healthcloud.repositories.PatientRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class PatientService implements IPatientService{

    PatientRepository patientRepository;
    DossierMRepository dossierMRepository;


    @Override
    public Patient ajouterPatient(Patient patient) {
        return patientRepository.save(patient);
    }

    @Override
    public Patient modifierPatient( Patient patient) {
        return patientRepository.save(patient);
    }

    @Override
    public List<Patient> afficherPatients() {
        return patientRepository.findAll();
    }

    @Override
    public Patient afficherPatient( int id) {
        return patientRepository.findById(id).orElse(null);
    }

    @Override
    public void supprimerPatient(int id) {
        patientRepository.deleteById(id);

    }

    @Override
    public Patient assignerDossierToPatient(Integer idP, Integer idD) {
        Patient patient = patientRepository.findById(idP).orElse(null);
        DossierMedical dossierMedical = dossierMRepository.findById(idD).orElse(null);
        patient.setDossierMedical(dossierMedical);
        return patientRepository.save(patient);
    }  }