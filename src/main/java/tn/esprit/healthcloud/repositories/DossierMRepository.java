package tn.esprit.healthcloud.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.healthcloud.entities.DossierMedical;

@Repository
public interface DossierMRepository extends JpaRepository<DossierMedical, Integer> {
}
