package tn.esprit.healthcloud.Repositories;

import tn.esprit.healthcloud.Entities.BanqueSang;

import tn.esprit.healthcloud.Entities.TypeSanguin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BanqueRepository extends JpaRepository<BanqueSang,Integer> {
    BanqueSang findByTypeSanguinDisponibleAndDonateurIsNull(TypeSanguin typeSanguin);
    BanqueSang findByTypeSanguinDisponible(TypeSanguin typeSanguinDisponible);
}
