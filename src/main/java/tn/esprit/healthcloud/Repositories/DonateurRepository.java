package tn.esprit.healthcloud.Repositories;

import tn.esprit.healthcloud.Entities.Donateur;
import tn.esprit.healthcloud.Entities.TypeSanguin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DonateurRepository extends JpaRepository<Donateur,Long> {
    List<Donateur> findByTypeSanguinAndQuantitéTotaleGreaterThan(TypeSanguin typeSanguin, int quantitéTotale);
}
