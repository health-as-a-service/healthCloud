package tn.esprit.healthCloud.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.healthCloud.entities.ERole;
import tn.esprit.healthCloud.entities.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Integer> {
    Optional<Role> findByName(ERole name);
}
