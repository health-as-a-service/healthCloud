package tn.esprit.healthcloud.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.healthcloud.entities.Medecin;

public interface MedecinRepositrory extends JpaRepository<Medecin,Integer> {
}
