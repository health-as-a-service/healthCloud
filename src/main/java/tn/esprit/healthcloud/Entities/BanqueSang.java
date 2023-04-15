package tn.esprit.healthcloud.Entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class BanqueSang implements Serializable {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int idBanque;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "idDonateur", referencedColumnName = "idDonateur")
        private Donateur donateur;

        @Enumerated(EnumType.STRING)
        @JoinColumn(name = "type_sanguin_id")
        private TypeSanguin typeSanguinDisponible;

        private int quantiteDisponible;

}