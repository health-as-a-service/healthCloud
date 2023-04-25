package tn.esprit.healthcloud.entities;

import lombok.*;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

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
    @JsonIgnore
    private Patient patient;
    
   
}

