package tn.esprit.etudiant.healthcloud.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.etudiant.healthcloud.entities.Administration;

public interface AdministrationRepository extends JpaRepository<Administration,Integer> {
}
