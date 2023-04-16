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
public class DossierMedical implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int idD;
    @Temporal(TemporalType.DATE)
    Date dateCreation;
    String typeMaladie;
    String traitement;
    String maladiesChroniques;
    String testsMedicaux;
    @Temporal(TemporalType.DATE)
    Date derniereMaj;


    //dossierM-Patient
    @OneToOne(mappedBy = "dossierMedical", cascade = {CascadeType.ALL})
    private Patient patient;
}

