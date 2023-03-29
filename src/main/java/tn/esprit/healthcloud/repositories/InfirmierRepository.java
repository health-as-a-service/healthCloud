package tn.esprit.healthcloud.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.healthcloud.entities.Infirmier;

public interface InfirmierRepository extends JpaRepository<Infirmier,Integer> {
}
