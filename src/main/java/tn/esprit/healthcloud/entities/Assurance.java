package tn.esprit.healthcloud.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Assurance implements Serializable {


        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private float montantAssurance;

        private String nomAgence;

        private String adresseAgence;

       @OneToMany(mappedBy = "assurance",cascade = CascadeType.ALL)
        @JsonIgnoreProperties
        private List<Consultation> consultations;

    }
