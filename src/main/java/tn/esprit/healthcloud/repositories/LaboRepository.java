package tn.esprit.healthcloud.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.healthcloud.entities.Laboratoire;

@Repository

public interface LaboRepository extends JpaRepository<Laboratoire,Integer> {
}
