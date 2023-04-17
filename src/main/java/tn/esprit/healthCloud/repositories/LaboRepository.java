package tn.esprit.healthCloud.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.healthCloud.entities.Laboratoire;

@Repository

public interface LaboRepository extends JpaRepository<Laboratoire,Integer> {

}
