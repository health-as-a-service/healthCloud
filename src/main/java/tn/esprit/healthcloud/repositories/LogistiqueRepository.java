package esprit.etudiant.tn.healthcloud.repositories;

import esprit.etudiant.tn.healthcloud.entities.Operation;
import org.springframework.data.jpa.repository.JpaRepository;

import esprit.etudiant.tn.healthcloud.entities.Logistique;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface LogistiqueRepository extends JpaRepository<Logistique, Integer> {

    List<Logistique> findByOperations(Operation operation);


    List<Logistique> findByTypeLogiContainingIgnoreCase(String typeLogi);

}

