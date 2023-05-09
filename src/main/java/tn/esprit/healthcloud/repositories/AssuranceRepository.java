package tn.esprit.healthcloud.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.healthcloud.entities.Assurance;


public interface AssuranceRepository extends JpaRepository<Assurance, Long> {

    }

