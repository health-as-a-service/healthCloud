package tn.esprit.healthCloud.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.healthCloud.entities.Pharmacie;

@Repository

public interface PharmacieRepository extends JpaRepository<Pharmacie,Integer> {
}
