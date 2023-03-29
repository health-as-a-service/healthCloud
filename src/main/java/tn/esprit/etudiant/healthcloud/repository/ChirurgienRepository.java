package tn.esprit.etudiant.healthcloud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.etudiant.healthcloud.entities.Chirurgien;

public interface ChirurgienRepository extends JpaRepository<Chirurgien,Integer> {
}

