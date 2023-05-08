package tn.esprit.healthcloud.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.healthcloud.entities.Assurance;
import tn.esprit.healthcloud.entities.Facture;


public interface FactureRepository extends JpaRepository<Facture, Long> {

    }
