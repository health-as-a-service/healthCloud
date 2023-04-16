package tn.esprit.healthcloud.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class Patient implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int idP;
    String nomP;
    String prenomP;
    @Temporal(TemporalType.DATE)
    Date dateNaissanceP;
    String numTel;
    long chambre;
    int sejour;


    //patient-dossierM
    @OneToOne(cascade = CascadeType.ALL)
    private DossierMedical dossierMedical;


}