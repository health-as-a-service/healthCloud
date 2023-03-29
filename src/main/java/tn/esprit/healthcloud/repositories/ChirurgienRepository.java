package tn.esprit.healthcloud.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.healthcloud.entities.Chirurgien;

public interface ChirurgienRepository extends JpaRepository<Chirurgien,Integer> {
}

