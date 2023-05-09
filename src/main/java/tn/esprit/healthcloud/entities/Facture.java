package tn.esprit.healthcloud.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Facture implements Serializable {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private float somme;

        private String typePaiement;

        @OneToOne(mappedBy = "facture", cascade = CascadeType.ALL)

        private Consultation consultations;

}
