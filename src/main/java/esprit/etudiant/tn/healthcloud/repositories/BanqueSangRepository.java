package esprit.etudiant.tn.healthcloud.repositories;

import esprit.etudiant.tn.healthcloud.entities.BanqueSang;
import esprit.etudiant.tn.healthcloud.entities.TypeSanguin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BanqueSangRepository extends JpaRepository<BanqueSang, Integer> {
    BanqueSang findByTypeSanguin(TypeSanguin typeSanguin);

}
