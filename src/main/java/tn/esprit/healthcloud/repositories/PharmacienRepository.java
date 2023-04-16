package tn.esprit.healthcloud.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import tn.esprit.healthcloud.entities.Pharmacien;

@Repository
public interface PharmacienRepository extends JpaRepository<Pharmacien,Integer> {

}
