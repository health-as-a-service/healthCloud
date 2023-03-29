package tn.esprit.etudiant.healthcloud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.etudiant.healthcloud.entities.Infirmier;

public interface InfirmierRepository extends JpaRepository<Infirmier,Integer> {
}
