package tn.esprit.etudiant.healthcloud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.etudiant.healthcloud.entities.Medecin;

public interface MedecinRepositrory extends JpaRepository<Medecin,Integer> {
}
