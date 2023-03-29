package tn.esprit.healthcloud.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.healthcloud.entities.Administration;

public interface AdministrationRepository extends JpaRepository<Administration,Integer> {
}
