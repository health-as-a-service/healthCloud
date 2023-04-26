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
public class Facture {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private float somme;

        private String typePaiement;

        @OneToOne (mappedBy = "facture")
        private Consultation consultations;

    }

