package tn.esprit.healthcloud.entities;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Assurance {


        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private float montantAssurance;

        private String nomAgence;

        private String adresseAgence;

        @OneToMany(mappedBy = "assurance")
        private List<Consultation> consultations;

    }
