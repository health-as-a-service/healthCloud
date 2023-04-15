package esprit.etudiant.tn.healthcloud.repositories;

import esprit.etudiant.tn.healthcloud.entities.Chirurgien;
import esprit.etudiant.tn.healthcloud.entities.Operation;
import esprit.etudiant.tn.healthcloud.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChirurgienRepository extends JpaRepository<Chirurgien, Integer> {



}

