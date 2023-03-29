package tn.esprit.healthcloud.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.healthcloud.entities.Stagiare;

public interface StagiareRepository extends JpaRepository<Stagiare,Integer> {
}
