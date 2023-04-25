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
public class Patient implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int idP;
    String nomP;
    String prenomP;
    String Email;
    String sexe;
    @Temporal(TemporalType.DATE)
    Date dateNaissanceP;
    String numTel;
    long chambre;
    long sejour;
    Boolean isArchive;
    LocalDateTime dateCreation;
    LocalDateTime dateArchivage;
 

    //patient-dossierM
    @OneToOne(cascade = CascadeType.ALL)
    private DossierMedical dossierMedical;
    
    @OneToOne(cascade = CascadeType.ALL)
    private Image image;


}