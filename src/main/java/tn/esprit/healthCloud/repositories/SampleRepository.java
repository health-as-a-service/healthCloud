package tn.esprit.healthCloud.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.healthCloud.entities.Sample;

@Repository
public interface SampleRepository extends JpaRepository<Sample,Integer> {
}
