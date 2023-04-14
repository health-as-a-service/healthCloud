package esprit.etudiant.tn.healthcloud.services;

import esprit.etudiant.tn.healthcloud.entities.Patient;
import esprit.etudiant.tn.healthcloud.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PatientService implements IPatientService {

    @Autowired
    private PatientRepository patientRepository;

    @Override
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    @Override
    public Patient getPatientById(int id) {
        return patientRepository.findById(id).orElse(null);
    }

    @Override
    public Patient saveOrUpdatePatient(Patient patient) {
        return patientRepository.save(patient);
    }

    @Override
    public void deletePatient(int id) {
        patientRepository.deleteById(id);
    }
}