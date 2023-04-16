package tn.esprit.healthcloud.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.healthcloud.entities.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {
}
