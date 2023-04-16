package tn.esprit.healthcloud.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.healthcloud.entities.TechnicienBiologist;

@Repository
public interface TechnicienBiologistRepository extends JpaRepository<TechnicienBiologist,Integer> {
}
