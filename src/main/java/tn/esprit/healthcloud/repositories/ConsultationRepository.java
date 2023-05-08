package tn.esprit.healthcloud.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.healthcloud.entities.Consultation;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;


@Repository
public interface ConsultationRepository extends JpaRepository<Consultation, Long> {

        @Modifying
        @Query("UPDATE Consultation c SET c.assurance.id = :assuranceId WHERE c.id_consultation = :consultationId")
        void addAssuranceToConsultation(@Param("consultationId") Long consultationId, @Param("assuranceId") Long assuranceId);

        List<Consultation> findByDateAfter(LocalDateTime startTime);

        @Query("SELECT c FROM Consultation c WHERE c.date > :currentDate")
        List<Consultation> findUpcomingConsultations(@Param("currentDate") Date currentDate);
}

