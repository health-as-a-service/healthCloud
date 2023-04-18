package tn.esprit.healthcloud.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.healthcloud.entities.Medicament;

@Repository
public interface MedicamentRepository extends JpaRepository<Medicament,Integer> {

}
